package com.example.servicehi.controller;

import com.alibaba.fastjson.JSON;
import com.example.servicehi.common.CodeMsg;
import com.example.servicehi.entity.HotWord;
import com.example.servicehi.entity.ShareTicketImg;
import com.example.servicehi.handler.GlobalException;
import com.example.servicehi.service.HotWordService;
import com.example.servicehi.service.ShareTicketImgService;
import com.example.servicehi.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RequestMapping(value = "/ShareTicketImg")
@RestController
@AllArgsConstructor
@Slf4j
public class ShareTicketImgController {
    private final ShareTicketImgService<ShareTicketImg> shareTicketImgService;

    private final HotWordService<HotWord> hotWordService;

    private final RedisTemplate redisTemplate;

    /**
     * 根据关键词搜索
     *
     * @param shareTicketImg
     * @return
     */
    @PostMapping(value = "/selectQRcodeAndTitle")
    public ResponseUtil selectQRcodeAndTitle(@RequestBody ShareTicketImg shareTicketImg) {
        return new ResponseUtil(shareTicketImgService.selectTitleAndTips(shareTicketImg));
    }

    /**
     * 获取热门搜索单词
     *
     * @return
     */
    @PostMapping(value = "/selectTopTenHotWord")
    public ResponseUtil selectTopTenHotWord() {
        HashMap<String, List<HotWord>> hotWordMap = new HashMap<>();
        hotWordMap.put("oneDaysBeforeHotWords", JSON.parseObject(redisTemplate.opsForValue().get("oneDaysBeforeHotWords").toString(), List.class));
        hotWordMap.put("threeDaysBeforeHotWords", JSON.parseObject(redisTemplate.opsForValue().get("threeDaysBeforeHotWords").toString(), List.class));
        hotWordMap.put("aWeekBeforeHotWords", JSON.parseObject(redisTemplate.opsForValue().get("aWeekBeforeHotWords").toString(), List.class));
        hotWordMap.put("aMonthBeforeHotWords", JSON.parseObject(redisTemplate.opsForValue().get("aMonthBeforeHotWords").toString(), List.class));
        hotWordMap.put("todayHotWords", hotWordService.selectTodayHotWord());
        return new ResponseUtil(hotWordMap);
    }
}