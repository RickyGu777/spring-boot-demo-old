package com.example.servicehi.util;

import com.alibaba.fastjson.JSON;
import com.example.servicehi.common.CodeMsg;
import com.example.servicehi.handler.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Slf4j
public class SaveAndPostImg {

    public static String compress(MultipartFile multipartFile, String path, String fileName) throws IOException {
        File dest = new File(path + fileName); // 保存位置
        FileUtil.checkParentFile(dest);
        multipartFile.transferTo(dest);
        return upload(dest);
    }

    public static String compressToCut(BufferedImage bufferedImage, String path, String fileName) throws IOException {
        File dest = new File(path + fileName); // 保存位置
        FileUtil.checkParentFile(dest);
        ImageIO.write(bufferedImage, "JPEG", dest);
        return upload(dest);
    }

    public static ResponseUtil sendImage(String randomName) throws IOException {
        File file = new File(randomName);
        MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, "--------------------HV2ymHFg03ehbqgZCaKO6jyH", Charset.defaultCharset());
        multipartEntity.addPart("img", new FileBody(file));

        HttpPost request = new HttpPost("https://www.finalfantasyxivfan.com/api/Image/backUpImage");
        request.setEntity(multipartEntity);
        request.addHeader("Content-Type", "multipart/form-data; boundary=--------------------HV2ymHFg03ehbqgZCaKO6jyH");

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(request);
        InputStream is = response.getEntity().getContent();
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        log.info("发送消息收到的返回：" + buffer.toString());
        return JSON.parseObject(buffer.toString(), ResponseUtil.class);
    }

    /**
     * 模拟上传图片
     *
     * @param fileName 图片路径
     */
    public static ResponseUtil uploadFileWithHttpMime(String fileName) throws IOException {
        // 定义请求url
        String uri = "https://www.finalfantasyxivfan.com/api/Image/QRCode";
        // 实例化http客户端
        HttpClient httpClient = new DefaultHttpClient();
        // 实例化post提交方式
        HttpPost post = new HttpPost(uri);
        // 添加json参数
        // 实例化参数对象
        MultipartEntity params = new MultipartEntity();
//            // 图片文本参数
//            params.addPart("textParams", new StringBody(
//                    "{'user_name':'我的用户名','channel_name':'却道明','channel_address':'(123.4,30.6)'}",
//                    Charset.forName("UTF-8")));
        // 设置上传文件
        File file = new File(fileName);
        // 文件参数内容
        FileBody fileBody = new FileBody(file);
        // 添加文件参数
        params.addPart("file", fileBody);
//            params.addPart("photoName", new StringBody(file.getName()));
        // 将参数加入post请求体中
        post.setEntity(params);
        // 执行post请求并得到返回对象 [ 到这一步我们的请求就开始了 ]
        HttpResponse resp = httpClient.execute(post);
        // 解析返回请求结果
        HttpEntity entity = resp.getEntity();
        InputStream is = entity.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String temp;
        while ((temp = reader.readLine()) != null) {
            buffer.append(temp);
        }
        System.out.println(buffer);
        return JSON.parseObject(buffer.toString(), ResponseUtil.class);
    }

    public static String sendImageToTD(MultipartFile multipartFile, String path, String fileName) throws IOException {
        String postUrl = "https://api.images.ac.cn/?type=multipart";
//        String postUrl = "https://sm.ms/api/upload";
        File dest = new File(path + File.separator + fileName); // 保存位置
        FileUtil.checkParentFile(dest);
        multipartFile.transferTo(dest);

        Map<String, String> fileMap = new HashMap<>();
        fileMap.put("file", dest.getPath());

        String res = "";
        HttpURLConnection conn = null;
        String BOUNDARY = "----WebKitFormBoundarypAIqI1RWBfPWiOKq"; //boundary就是request头和上传文件内容的分隔符
        try {
            URL url = new URL(postUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + BOUNDARY);
            conn.setRequestProperty("Referer", "https://images.ac.cn/cn.html");
            conn.setRequestProperty("Origin", "https://images.ac.cn");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundaryPp9Cj8sAT1oPKOFC");


            OutputStream out = new DataOutputStream(conn.getOutputStream());

            // file
            if (fileMap != null) {
                Iterator iter = fileMap.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String inputName = (String) entry.getKey();
                    String inputValue = (String) entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    File file = new File(inputValue);
                    String filename = file.getName();
                    String contentType = new MimetypesFileTypeMap()
                            .getContentType(file);
                    if (filename.endsWith(".png")) {
                        contentType = "image/png";
                    }
                    if (filename.endsWith(".gif")) {
                        contentType = "image/gif";
                    }
                    if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) {
                        contentType = "image/jpeg";
                    }

                    StringBuffer strBuf = new StringBuffer();
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append(
                            "\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\""
                            + inputName + "\"; filename=\"" + filename
                            + "\"\r\n");
                    strBuf.append("Content-Type:" + contentType + "\r\n\r\n");

                    out.write(strBuf.toString().getBytes());

                }
            }

            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();

            // 读取返回数据
            StringBuffer strBuf = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                strBuf.append(line).append("\n");
            }
            res = strBuf.toString();
            reader.close();
        } catch (Exception e) {
            log.info("发送POST请求出错:[{}]", postUrl);
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return res;
    }

    private static String upload(File dest) {
        String postUrl = "https://sm.ms/api/upload";
        Map<String, String> textMap = new HashMap<>();
        textMap.put("file_id", dest.getPath());

        Map<String, String> fileMap = new HashMap<>();
        fileMap.put("smfile", dest.getPath());

        String res;
        HttpURLConnection conn = null;
        String BOUNDARY = "----WebKitFormBoundarypAIqI1RWBfPWiOKq"; //boundary就是request头和上传文件内容的分隔符
        try {
            URL url = new URL(postUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn
                    .setRequestProperty("User-Agent",
                            "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + BOUNDARY);

            OutputStream out = new DataOutputStream(conn.getOutputStream());

            // file
            if (fileMap != null) {
                Iterator iter = fileMap.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String inputName = (String) entry.getKey();
                    String inputValue = (String) entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    File file = new File(inputValue);
                    String filename = file.getName();
                    String contentType = new MimetypesFileTypeMap()
                            .getContentType(file);
                    if (filename.endsWith(".png")) {
                        contentType = "image/png";
                    }
                    if (filename.endsWith(".gif")) {
                        contentType = "image/gif";
                    }
                    if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) {
                        contentType = "image/jpeg";
                    }

                    StringBuffer strBuf = new StringBuffer();
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append(
                            "\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\""
                            + inputName + "\"; filename=\"" + filename
                            + "\"\r\n");
                    strBuf.append("Content-Type:" + contentType + "\r\n\r\n");

                    out.write(strBuf.toString().getBytes());

                }
            }

            // text
            if (textMap != null) {
                StringBuffer strBuf = new StringBuffer();
                Iterator iter = textMap.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String inputName = (String) entry.getKey();
                    String inputValue = (String) entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append(
                            "\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\""
                            + inputName + "\"\r\n\r\n");

                    File file = new File(inputValue);
                    DataInputStream in = new DataInputStream(
                            new FileInputStream(file));
                    int bytes;
                    byte[] bufferOut = new byte[1024];
                    while ((bytes = in.read(bufferOut)) != -1) {
                        out.write(bufferOut, 0, bytes);
                    }
                    in.close();
                }
                out.write(strBuf.toString().getBytes());
            }

            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();

            // 读取返回数据
            StringBuffer strBuf = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                strBuf.append(line).append("\n");
            }
            res = strBuf.toString();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(CodeMsg.SAVE_IMG_POST_REQUEST_ERROR);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return res;
    }
}
