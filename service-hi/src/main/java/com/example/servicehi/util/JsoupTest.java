package com.example.servicehi.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.servicehi.entity.AhriUrl;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class JsoupTest {
    private volatile static List<AhriUrl> urlList = new ArrayList<AhriUrl>();
    private volatile static BlockingQueue<AhriUrl> bq = new ArrayBlockingQueue<AhriUrl>(10);

    private static JsoupTest JSOUP_TEST = new JsoupTest();

    private static boolean begin = true;

    public static String BASE_URL = "https://ahri.host";

    public synchronized static void addUrl(List<AhriUrl> url) {
//        urlList.addAll(url);
        bq.addAll(url);
        System.err.println(bq.size());
        System.err.println("add");
    }

    public synchronized static void removeUrl(AhriUrl ahriUrl) {
//        urlList.remove(ahriUrl);
        bq.remove(ahriUrl);
    }

    public static JsoupTest getInstance() {
        return JSOUP_TEST;
    }

    public void Download() {
        if (begin) {
            begin = false;
            Thread thread = new Thread(new Begin());
            thread.start();
            System.err.println("Begin begin");
        }
    }

    class Begin implements Runnable {

        @Override
        public void run() {
            while (!JsoupTest.begin) {
                System.err.println("开启线程");
//                System.err.println(urlList.size());
                System.err.println(bq.size());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!bq.isEmpty()) {
                    try {
                        for (AhriUrl ahriUrl : bq) {
                            String s = CheckURL.checkUrlType(ahriUrl.getUrl());
                            switch (s) {
                                case "1":
                                    try {
                                        dnewUrl.getComicList(ahriUrl.getUrl());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case "2":
                                    try {
                                        innerUrl.getUrl(ahriUrl.getUrl());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                default:
                                    System.err.println(ahriUrl);
                                    System.err.println("未匹配到URL规则");
                                    break;
                            }
                            removeUrl(ahriUrl);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.err.println("重复开始");
            }
            System.err.println("Begin end");
        }
    }
}

class GetConnection {
    public static Connection getConnection(String url) {
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

/**
 * 分析URL
 */
class CheckURL {
    public static String checkUrlType(String urlAddress) {
        String type = null;
        if (urlAddress.contains("dnew.php")) {
            // 最外层详情页
            type = "1";
        } else if (urlAddress.contains("/post.php?ID=")) {
            type = "2";
        }
        return type;
    }
}

/**
 * 最外层链接分析
 */
class dnewUrl {
    private String baseUrl;

    public static void getComicList(String url) throws Exception {
        Connection connect = GetConnection.getConnection(url); // 外部首页分析
        Document doc = connect.get();
        List<Node> nodes = doc.body().childNodes().get(3).childNodes().get(1).childNodes().get(3).childNodes();

        for (Node node : nodes) {
            if (node.childNodes().isEmpty()
                    || ((Element) node).text().contains("動畫")
                    || ((Element) node).text().contains("線上影片")
                    || ((Element) node).text().contains("只供下載")
                    || ((Element) node).tagName().equals("style")) {
                continue;
            }
            String img_cover_url = ((Element) node.childNodes().get(0)).getElementsByTag("img").attr("src");
            String comic_name = ((Element) node.childNodes().get(1)).getElementsByTag("a").text();
            String comic_info_url = JsoupTest.BASE_URL + ((Element) node.childNodes().get(1)).getElementsByTag("a").attr("href");

            innerUrl.getUrl(comic_info_url);
        }
    }
}

/**
 * 详情页下载
 */
class innerUrl {
    //    public static String BASE_PATH = "/mnt/windows_file/";
    public static String BASE_PATH = "E://share//";

    public static void getUrl(String comic_info_url) throws Exception {
        Connection connection = GetConnection.getConnection(comic_info_url);
        Document document = connection.get();
        // 文件夹名称
        String text = document.body().getElementsByClass("page-title txt-color-blueDark").get(0).getElementsByTag("a").text();
        String href = JsoupTest.BASE_URL + "/" + document.body().getElementsByClass("btn btn-white btn-default").get(0).attr("href");
        ComicUrl.getPictureUrl(href, text);
    }
}

/**
 * 图片展示
 */
class ComicUrl {
    //    public static String BASE_PATH = "/mnt/windows_file/";
    public static String BASE_PATH = "E://share//";

    public static void getPictureUrl(String url, String file) throws Exception {
        Connection connect = GetConnection.getConnection(url); // 外部首页分析
        Document doc = connect.get();
        Elements script1 = doc.body().getElementsByTag("script");
        for (Element element : script1) {
            if (element.html().contains("HTTP_IMAGE")) {
                String http = element.html().split("Original_Image_List")[0].replace("var HTTP_IMAGE = \"", "").replace("\"; var ", "");
                List<Map> original_image_list = JSON.parseObject(element.html().split("Original_Image_List")[2].split(";")[0].replace(" = ", ""), new TypeReference<List<Map>>() {
                });
                ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
                for (int i = 0; i < original_image_list.size(); i++) {
                    String new_filename = original_image_list.get(i).get("new_filename").toString();
                    DownLoadPicture downLoadPicture = new DownLoadPicture();
                    downLoadPicture.setDownPath(BASE_PATH + file);
                    if (i < 10) {
                        downLoadPicture.setFileName("000" + i + "_" + new_filename + "_w1500." + original_image_list.get(i).get("extension"));
                    } else if (10 <= i && i < 100) {
                        downLoadPicture.setFileName("00" + i + "_" + new_filename + "_w1500." + original_image_list.get(i).get("extension"));
                    } else if (100 <= i && i < 1000) {
                        downLoadPicture.setFileName("0" + i + "_" + new_filename + "_w1500." + original_image_list.get(i).get("extension"));
                    } else if (1000 <= i && i < 10000) {
                        downLoadPicture.setFileName(i + "_" + new_filename + "_w1500." + original_image_list.get(i).get("extension"));
                    }
                    downLoadPicture.setFileUrl(http + new_filename + "_w1500." + original_image_list.get(i).get("extension"));
                    fixedThreadPool.execute(downLoadPicture);
                }
                fixedThreadPool.shutdown();
//                for (Map map : original_image_list) {
//                    String new_filename = map.get("new_filename").toString();
//                    DownLoadPicture downLoadPicture = new DownLoadPicture();
//                    downLoadPicture.setDownPath(BASE_PATH+file);
//                    downLoadPicture.setFileName(new_filename + "_w1500." + map.get("extension"));
//                    downLoadPicture.setFileUrl(http + new_filename + "_w1500." + map.get("extension"));
//                    Thread thread = new Thread(downLoadPicture);
//                    thread.start();
//                    DownLoadPicture.downPictureUrl(new_filename + "_w1500." + map.get("extension"),http + new_filename + "_w1500." + map.get("extension"), BASE_PATH+file);
//                }
            }
        }
    }
}

class DownLoadPicture implements Runnable {
    private String downPath;
    private String fileName;
    private String fileUrl;

    public void setDownPath(String downPath) {
        this.downPath = downPath;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    //    public static void downPictureUrl(String fileName, String fileUrl, String downPath) {
//        File savePath = new File(downPath);
//        if (!savePath.exists()) {
//            savePath.mkdir();
//        }
//        String[] urlname = fileUrl.split("/");
//        int len = urlname.length - 1;
//        String uname = urlname[len];//获取文件名
//        try {
//            File file = new File(savePath + "/" + uname);//创建新文件
//            if (file != null && !file.exists()) {
//                file.createNewFile();
//            }
//            OutputStream oputstream = new FileOutputStream(file);
//            URL url = new URL(fileUrl);
//            URLConnection uc;
//            uc = url.openConnection();
//            uc.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0;WindowsNT 5.0)");
//            uc.setDoInput(true);//设置是否要从 URL 连接读取数据,默认为trueuc.connect;
//            InputStream iputstream = uc.getInputStream();
////            System.out.println("filesize is:" + uc.getContentLength());//打印文件长度
//            byte[] buffer = new byte[1024 * 1024 * 5]; // 5MB刷新缓存
//            int byteRead = -1;
//            while ((byteRead = (iputstream.read(buffer))) != -1) {
//                oputstream.write(buffer, 0, byteRead);
//            }
//            oputstream.flush();
//            iputstream.close();
//            oputstream.close();
//        } catch (Exception e) {
//            System.out.println("读取失败！");
//            e.printStackTrace();
//        }
//
//    }

    @Override
    public void run() {
        System.err.println("文件名" + fileName);
        System.err.println("生成文件路径：" + downPath);
        System.err.println("下载路径" + fileUrl);
        File savePath = new File(downPath);
        if (!savePath.exists()) {
            savePath.mkdir();
        }
//        String[] urlname = fileUrl.split("/");
//        int len = urlname.length - 1;
//        String uname = urlname[len];//获取文件名
        try {
            File file = new File(savePath + "/" + fileName);//创建新文件
            if (file != null && !file.exists()) {
                file.createNewFile();
            }
            OutputStream oputstream = new FileOutputStream(file);
            URL url = new URL(fileUrl);
            URLConnection uc;
            uc = url.openConnection();
            uc.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0;WindowsNT 5.0)");
            uc.setDoInput(true);//设置是否要从 URL 连接读取数据,默认为trueuc.connect;
            InputStream iputstream = uc.getInputStream();
//            System.out.println("filesize is:" + uc.getContentLength());//打印文件长度
            byte[] buffer = new byte[1024 * 1024 * 5]; // 5MB刷新缓存
            int byteRead = -1;
            while ((byteRead = (iputstream.read(buffer))) != -1) {
                oputstream.write(buffer, 0, byteRead);
            }
            oputstream.flush();
            iputstream.close();
            oputstream.close();
        } catch (Exception e) {
            System.out.println("读取失败！");
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}