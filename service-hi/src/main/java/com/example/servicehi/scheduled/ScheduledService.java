package com.example.servicehi.scheduled;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.servicehi.entity.HotWord;
import com.example.servicehi.entity.IpRecord;
import com.example.servicehi.service.HotWordService;
import com.example.servicehi.service.IpRecordService;
import com.example.servicehi.util.Baidu.BaiduTool;
import com.sun.org.apache.bcel.internal.generic.CALOAD;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;
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

    public void scannerFile() {
        List<File> fileSort = getFileSort("D:\\images");
        // 先扫描本地的QQ图片文件夹
        // 扫描所有.jpg格式结尾的文件
        // 然后上传识别
        // 根据返回值判断是否识别成功
        // 如果未识别成功的，则放入统一文件夹中
//        new hashmap
    }

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

}