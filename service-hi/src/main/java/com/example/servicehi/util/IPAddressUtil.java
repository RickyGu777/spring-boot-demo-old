package com.example.servicehi.util;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 查询外网IP工具类
 */
@Slf4j
public class IPAddressUtil {
    private String searchIPWebSite;

    private static IPAddressUtil ipAddressUtil;

    private IPAddressUtil() {
    }

    public static IPAddressUtil getInstance(String webSite){
        if (ipAddressUtil == null) {
            ipAddressUtil = new IPAddressUtil();
            ipAddressUtil.searchIPWebSite = webSite;
            return ipAddressUtil;
        } else {
            return ipAddressUtil;
        }
    }

    /**
     * 获得外网IP
     *
     * @return 外网IP
     */
    public String getV4IP() {
        URL url;
        URLConnection urlconn;
        BufferedReader br = null;
        try {
            url = new URL(searchIPWebSite);//爬取的网站是百度搜索ip时排名第一的那个
            urlconn = url.openConnection();
            br = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));
            String buf;
            String get = null;
            while ((buf = br.readLine()) != null) {
                get += buf;
            }
            int where, end;
            for (where = 0; where < get.length() && get.charAt(where) != '['; where++) ;
            for (end = where; end < get.length() && get.charAt(end) != ']'; end++) ;
            get = get.substring(where + 1, end);
            return get;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "0.0.0.0";
    }

    /**
     * 获得内网IP
     *
     * @return 内网IP
     */
    public String getIntranetIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        log.info("=====");
        log.info(ip);
        log.info("=====");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

    private Connection getConnection(String url) {
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