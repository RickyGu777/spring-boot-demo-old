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

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
}