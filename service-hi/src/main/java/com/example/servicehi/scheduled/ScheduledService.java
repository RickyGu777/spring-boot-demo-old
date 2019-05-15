package com.example.servicehi.scheduled;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.servicehi.entity.HotWord;
import com.example.servicehi.service.HotWordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class ScheduledService {
    @Autowired
    private HotWordService<HotWord> hotWordService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Scheduled(cron = "0/30 * * * * ?")
    public void getHotWord() {
        log.info("getHotWord,Time:[{}]", new Date());
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

        log.info("oneDaysBeforeHotWords in redis:[{}]", redisTemplate.opsForValue().get("oneDaysBeforeHotWords"));
        log.info("threeDaysBeforeHotWords in redis:[{}]", redisTemplate.opsForValue().get("threeDaysBeforeHotWords"));
        log.info("aWeekBeforeHotWords in redis:[{}]", redisTemplate.opsForValue().get("aWeekBeforeHotWords"));
        log.info("aMonthBeforeHotWords in redis:[{}]", redisTemplate.opsForValue().get("aMonthBeforeHotWords"));
    }
}