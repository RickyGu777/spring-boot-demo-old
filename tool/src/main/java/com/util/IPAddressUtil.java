package com.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.InetAddress;

/**
 * 查询外网IP工具类
 */
public class IPAddressUtil {
    private IPAddressUtil() {
    }

    /**
     * 获得外网IP
     *
     * @return 外网IP
     */
    public static String getV4IP() throws IOException {
        Connection connection = getConnection("https://www.ip.cn/");
        Document document = connection.get();
        Element body = document.body();
        Element element = body.getElementsByClass("well").get(0).getElementsByTag("code").get(0);
        return element.text();
    }

    /**
     * 获得内网IP
     *
     * @return 内网IP
     */
    public static String getIntranetIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection getConnection(String url) {
        Connection connect = Jsoup.connect(url);
        connect.header("Accept", "application/json, text/javascript, */*; q=0.01").header("Accept-Encoding", "gzip, deflate");
        connect.header("Accept-Language", "zh-CN,zh;q=0.9").header("Connection", "keep-alive");
        connect.header("Content-Length", "72");
        connect.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        connect.header("User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
        connect.header("X-Requested-With", "XMLHttpRequest");
        return connect;
    }
}