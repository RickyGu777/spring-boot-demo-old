package com.example.servicehi.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeChatMessageUtil {
    public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_VOICE = "voice";
    public static final String MESSAGE_VIDEO = "video";
    public static final String MESSAGE_LINK = "link";
    public static final String MESSAGE_LOCATION = "location";
    public static final String MESSAGE_EVENT = "event";

    public static final String EVENT_SUB = "subscribe";
    public static final String EVENT_UNSUB = "unsubscribe";
    public static final String EVENT_CLICK = "CLICK";
    public static final String EVENT_VIEW = "VIEW";

    /**
     * xml转为map
     *
     * @param request
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request) throws DocumentException, IOException {
        Map<String, String> map = new HashMap();

        SAXReader reader = new SAXReader();

        InputStream ins = request.getInputStream();
        Document doc = reader.read(ins);

        Element root = doc.getRootElement();
        List<Element> list = root.elements();
        for (Element e : list) {
            map.put(e.getName(), e.getText());
        }
        ins.close();
        return map;
    }

    public static String textMessageToTxtXml(TextMessage textMessage) {
        String msg = "<xml>"
                + "<ToUserName><![CDATA[" + textMessage.getToUserName() + "]]></ToUserName>" //回复用户时，这里是用户的openid；但用户发送过来消息这里是微信公众号的原始id
                + "<FromUserName><![CDATA[" + textMessage.getFromUserName() + "]]></FromUserName>" //这里填写微信公众号 的原始id；用户发送过来时这里是用户的openid
                + "<CreateTime>" + textMessage.getCreateTime() + "</CreateTime>" //这里可以填创建信息的时间，目前测试随便填也可以
                + "<MsgType><![CDATA[text]]></MsgType>" //文本类型，text，可以不改
                + "<Content><![CDATA[" + textMessage.getContent() + "]]></Content>" //文本内容，
                + "<MsgId>" + textMessage.getMsgId() + "</MsgId> " //消息id，随便填，但位数要够
                + "</xml>";
        return msg;

    }
}
