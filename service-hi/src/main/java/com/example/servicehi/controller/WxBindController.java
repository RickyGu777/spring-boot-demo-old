package com.example.servicehi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.servicehi.common.AccessToken;
import com.example.servicehi.common.Config;
import com.example.servicehi.entity.WeChatPublicAccount;
import com.example.servicehi.entity.WeChatPublicAccountResponseInfo;
import com.example.servicehi.service.WeChatPublicAccountResponseInfoService;
import com.example.servicehi.service.WeChatPublicAccountService;
import com.example.servicehi.util.HttpUtil;
import com.example.servicehi.util.RandomUtil;
import com.example.servicehi.util.ResponseUtil;
import com.example.servicehi.util.WeChatMessageUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@RequestMapping(value = "/Wx")
@RestController
@AllArgsConstructor
@Slf4j
public class WxBindController {
    private final Config config;

    private final WeChatPublicAccountService<WeChatPublicAccount> weChatPublicAccountService;

    private final WeChatPublicAccountResponseInfoService<WeChatPublicAccountResponseInfo> weChatPublicAccountResponseInfoService;

    @GetMapping
    public void get(HttpServletRequest request, HttpServletResponse response) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            if (checkSignature(signature, timestamp, nonce)) {
                writer.print(echostr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

    @PostMapping
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        log.info("doPost");
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            Map<String, String> map = WeChatMessageUtil.xmlToMap(request);
            String toUserName = map.get("ToUserName");
            String fromUserName = map.get("FromUserName");
            String content = map.get("Content");

            WeChatPublicAccount weChatPublicAccount = new WeChatPublicAccount();
            WeChatPublicAccountResponseInfo weChatPublicAccountResponseInfo = new WeChatPublicAccountResponseInfo();
            weChatPublicAccountResponseInfo.setRequestInfo(content);
            weChatPublicAccountResponseInfo = weChatPublicAccountResponseInfoService.selectByRequestInfo(weChatPublicAccountResponseInfo);
            String msg;

            if (weChatPublicAccountResponseInfo == null) {
                msg = "这里是FinalFantasyXIV爱好者的微信公众号，" +
                        "如果我的公众号有任何侵犯您版权的信息，" +
                        "请将发送邮件至544107550@qq.com，" +
                        "并在邮件中留下您的可靠的联系方式，我将尽快联系您。" +
                        "与您核实侵权信息后我将尽快删除。";
            } else {
                msg = weChatPublicAccountResponseInfo.getResponseInfo();
                weChatPublicAccount.setResponseInfoId(weChatPublicAccountResponseInfo.getId());
            }
            // 对用户发送过来的内容选择要回复的内容
            long time = new Date().getTime();
            String number = RandomUtil.createNumber(16);
            String replyMsg = "<xml>"
                    + "<ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>" //回复用户时，这里是用户的openid；但用户发送过来消息这里是微信公众号的原始id
                    + "<FromUserName><![CDATA[" + toUserName + "]]></FromUserName>" //这里填写微信公众号 的原始id；用户发送过来时这里是用户的openid
                    + "<CreateTime>" + time + "</CreateTime>" //这里可以填创建信息的时间，目前测试随便填也可以
                    + "<MsgType><![CDATA[text]]></MsgType>" //文本类型，text，可以不改
                    + "<Content><![CDATA[" + msg + "]]></Content>" //文本内容，
                    + "<MsgId>" + number + "</MsgId> " //消息id，随便填，但位数要够
                    + "</xml>";

            weChatPublicAccount.setMessageId(number);
            weChatPublicAccount.setWxOpenid(fromUserName);
            weChatPublicAccount.setUserSendInfo(content);
            weChatPublicAccount.setResponseInfo(msg);
            weChatPublicAccountService.insert(weChatPublicAccount);

            out.println(replyMsg);//回复
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/getOpenId")
    public ResponseUtil getOpenId(@RequestBody Map<String, String> map) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("appid", config.getWeChatAppId());
        params.put("secret", config.getWeChatAppSecret());
        params.put("code", map.get("code"));
        params.put("grant_type", "authorization_code");
        String json = HttpUtil.get("https://api.weixin.qq.com/sns/oauth2/access_token", params);
        Map<String, Object> stringObjectMap = JSON.parseObject(json, new TypeReference<Map<String, Object>>() {
        });
        return new ResponseUtil(stringObjectMap.get("openid"));
    }

    /**
     * getSignature
     */
    @RequestMapping(value = "/getSignature")
    public Object getSignature(HttpServletRequest request) throws Exception {
        String url = request.getParameter("url");
        String appid = config.getWeChatAppId();
        String accessToke = getAccessToken();
        String ticket = getTicket(accessToke);

        String noncestr = UUID.randomUUID().toString().replaceAll("-", "");
        String timestamp = Long.toString(new Date().getTime() / 1000);
        String str = "jsapi_ticket=" + ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] bytes = digest.digest(str.getBytes());
        String signature = byteToString(bytes);

        Map<String, String> map = new HashMap<>();
        map.put("appid", appid);
        map.put("noncestr", noncestr);
        map.put("signature", signature);
        map.put("timestamp", timestamp);
        map.put("url", url);
        return map;
    }

    @RequestMapping(value = "/getMenu")
    public ResponseUtil getMenu() throws Exception {
        String url = "https://api.weixin.qq.com/cgi-bin/menu/get";
        Map<String, String> params = new HashMap<>();
        params.put("access_token", getAccessToken());
        String json = HttpUtil.get(url, params);
        return new ResponseUtil(json);
    }

    private static String byteToString(byte[] bytes) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String tempStr = Integer.toHexString(bytes[i] & 0xff);
            if (tempStr.length() == 1) {
                buf.append("0").append(tempStr);
            } else {
                buf.append(tempStr);
            }
        }
        return buf.toString().toLowerCase();
    }

    /**
     * 获取access_token
     *
     * @return
     */
    private String getAccessToken() throws Exception {
        AccessToken instance = AccessToken.getInstance();
        System.out.println(instance);
        if (instance.getToken() == null || new Date().getTime() - instance.getCreateDate().getTime() > 7000) {
            instance.setCreateDate(new Date());
            String url = "https://api.weixin.qq.com/cgi-bin/token";
            HashMap<String, String> params = new HashMap<>();
            params.put("appid", config.getWeChatAppId());
            params.put("secret", config.getWeChatAppSecret());
            params.put("grant_type", "client_credential");

            String s = HttpUtil.get(url, params);
            Map map = JSON.parseObject(s, Map.class);
            instance.setToken(map.get("access_token").toString());
            return instance.getToken();
        }
        return instance.getToken();
    }

    private String getTicket(String accessToke) throws Exception {
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("type", "jsapi");
        hashMap.put("access_token", accessToke);
        String s = HttpUtil.get(url, hashMap);
        Map map = JSON.parseObject(s, Map.class);
        return map.get("ticket").toString();
    }

    /**
     * 校验签名
     */
    private boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[]{config.getWeChatToken(), timestamp, nonce};
        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }
}