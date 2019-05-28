package com.example.servicehi.scheduled;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.servicehi.common.Config;
import com.example.servicehi.entity.HotWord;
import com.example.servicehi.entity.IpRecord;
import com.example.servicehi.service.HotWordService;
import com.example.servicehi.service.IpRecordService;
import com.example.servicehi.util.Baidu.BaiduTool;
import com.example.servicehi.util.FileUtil;
import com.example.servicehi.util.ResponseUtil;
import com.example.servicehi.util.SaveAndPostImg;
import com.sun.org.apache.bcel.internal.generic.CALOAD;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class ScheduledService {
    @Autowired
    private HotWordService<HotWord> hotWordService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IpRecordService<IpRecord> ipRecordService;

    @Autowired
    private Config config;

    /**
     * 每晚23点55分执行
     */
    @Scheduled(cron = "0 55 23 * * ?")
    public void getHotWord() {
        Calendar instance = Calendar.getInstance();
        Set keys = redisTemplate.keys("IP-*-" + instance.get(Calendar.YEAR) + instance.get(Calendar.MONTH) + instance.get(Calendar.DATE));

        HotWord hotWord = new HotWord();
        hotWord.setTimes(1);
        List<HotWord> oneDaysBeforeHotWords = hotWordService.selectDateHotWord(hotWord);
        redisTemplate.opsForValue().set("oneDaysBeforeHotWords", JSON.toJSONString(oneDaysBeforeHotWords));
        hotWord.setTimes(3);
        List<HotWord> threeDaysBeforeHotWords = hotWordService.selectDateHotWord(hotWord);
        redisTemplate.opsForValue().set("threeDaysBeforeHotWords", JSON.toJSONString(threeDaysBeforeHotWords));
        hotWord.setTimes(7);
        List<HotWord> aWeekBeforeHotWords = hotWordService.selectDateHotWord(hotWord);
        redisTemplate.opsForValue().set("aWeekBeforeHotWords", JSON.toJSONString(aWeekBeforeHotWords));
        hotWord.setTimes(30);
        List<HotWord> aMonthBeforeHotWords = hotWordService.selectDateHotWord(hotWord);
        redisTemplate.opsForValue().set("aMonthBeforeHotWords", JSON.toJSONString(aMonthBeforeHotWords));
    }

    @Scheduled(cron = "0 0/30 0/1 * * ?")
    public void getBaiduAccessToken() {
        redisTemplate.opsForValue().set("baiduAccessToken", BaiduTool.getAuth());
        redisTemplate.expire("baiduAccessToken", 7200, TimeUnit.SECONDS);
    }

//    @Scheduled()
    public void scannerFile() throws IOException {
        List<File> backFileList = new ArrayList<>();
        // 先扫描本地的QQ图片文件夹，扫描所有.jpg格式结尾的文件，并按时间倒序排序文件
        List<File> fileSort = getFileSort(config.getQQFilePath());
        // 然后上传识别
        for (File file : fileSort) {
            if (SystemUtils.IS_OS_WINDOWS) {
                // 读取图片数据，用长宽作为参数过滤一些表情图片
                Image src = Toolkit.getDefaultToolkit().getImage(file.getPath());
                BufferedImage image = toBufferedImage(src);
                int width = image.getWidth();
                int height = image.getHeight();

                if (width > 500 && height > 900) {
                    ResponseUtil responseUtil = SaveAndPostImg.sendImage(config.getFilePath() + file.getName());
                    // 根据返回值判断是否识别成功
                    if (responseUtil.getCode() != 0) {
                        // 如果未识别成功的，先将未成功的文件路径放入一个List存放，循环结束后，将这些图片复制到统一文件夹
                        backFileList.add(file);
                    }
                }
            }
        }
        Calendar instance = Calendar.getInstance();

        // 复制图片文件
        for (File file : backFileList) {
            String backUpFilePath = config.getBackUpFilePath() + instance.get(Calendar.YEAR) + "-" + (instance.get(Calendar.MONTH) + 1) + "-" + instance.get(Calendar.DATE) + File.separator + file.getName();
            File outPutFile = new File(backUpFilePath);
            FileUtil.checkParentFile(outPutFile);
            copyFile(file, outPutFile);
        }
    }

//    public static void main(String[] args) throws IOException {
//        Calendar instance = Calendar.getInstance();
//
//        ScheduledService scheduledService = new ScheduledService();
//        List<File> fileSort = scheduledService.getFileSort("D:\\usr\\local\\app\\springboot\\jar\\service-hi");
//
//        for (File file : fileSort) {
//            String backUpFilePath = "D:\\backUpFile\\" + instance.get(Calendar.YEAR) + "-" + (instance.get(Calendar.MONTH) + 1) + "-" + instance.get(Calendar.DATE) + File.separator + file.getName();
//            File outPutFile = new File(backUpFilePath);
//            FileUtil.checkParentFile(outPutFile);
//            scheduledService.copyFile(file, outPutFile);
//        }
//    }

    /**
     * 获取目录下所有文件(按时间从新到旧排序)
     *
     * @param path
     * @return
     */
    public List<File> getFileSort(String path) {
        List<File> list = getFiles(path, new ArrayList());
        if (list != null && list.size() > 0) {
            Collections.sort(list, (file, newFile) -> {
                if (file.lastModified() < newFile.lastModified()) {
                    return 1;
                } else if (file.lastModified() == newFile.lastModified()) {
                    return 0;
                } else {
                    return -1;
                }
            });
        }
        return list;
    }

    /**
     * 获取目录下所有.jpg格式结尾的文件
     *
     * @param realpath
     * @param files
     * @return
     */
    public List<File> getFiles(String realpath, List<File> files) {
        File realFile = new File(realpath);
        if (realFile.isDirectory()) {
            File[] subfiles = realFile.listFiles();
            for (File file : subfiles) {
                if (file.isDirectory()) {
                    getFiles(file.getAbsolutePath(), files);
                } else {
                    if (file.getName().indexOf(".jpg") != -1) {
                        files.add(file);
                    }
                }
            }
        }
        return files;
    }

    /**
     * 读取图片
     *
     * @param image
     * @return
     */
    private static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        image = new ImageIcon(image).getImage();
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        try {
            int transparency = Transparency.OPAQUE;
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null),
                    image.getHeight(null), transparency);
        } catch (HeadlessException e) {
        }
        if (bimage == null) {
            int type = BufferedImage.TYPE_INT_RGB;
            bimage = new BufferedImage(image.getWidth(null),
                    image.getHeight(null), type);
        }
        Graphics g = bimage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return bimage;
    }

    /**
     * 复制文件
     *
     * @param sourceFile
     * @param targetFile
     * @throws IOException
     */
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        // 新建文件输入流并对它进行缓冲
        FileInputStream input = new FileInputStream(sourceFile);
        BufferedInputStream inBuff = new BufferedInputStream(input);

        // 新建文件输出流并对它进行缓冲
        FileOutputStream output = new FileOutputStream(targetFile);
        BufferedOutputStream outBuff = new BufferedOutputStream(output);

        // 缓冲数组
        byte[] b = new byte[1024 * 5];
        int len;
        while ((len = inBuff.read(b)) != -1) {
            outBuff.write(b, 0, len);
        }
        // 刷新此缓冲的输出流
        outBuff.flush();

        //关闭流
        inBuff.close();
        outBuff.close();
        output.close();
        input.close();
    }

    /**
     * 复制图片文件夹重点图片到目标文件夹
     *
     * @param sourceDir
     * @param targetDir
     * @throws IOException
     */
    public static void copyDirectiory(String sourceDir, String targetDir) throws IOException {
        // 新建目标目录
        (new File(targetDir)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDir)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 源文件
                File sourceFile = file[i];
                // 目标文件
                File targetFile = new
                        File(new File(targetDir).getAbsolutePath()
                        + File.separator + file[i].getName());
                copyFile(sourceFile, targetFile);
            }
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹
                String dir1 = sourceDir + File.separator + file[i].getName();
                // 准备复制的目标文件夹
                String dir2 = targetDir + File.separator + file[i].getName();
                copyDirectiory(dir1, dir2);
            }
        }
    }
}