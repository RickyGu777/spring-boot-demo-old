package com.example.servicehi.controller;

import com.alibaba.fastjson.JSON;
import com.example.servicehi.entity.HotWord;
import com.example.servicehi.entity.ShareTicketImg;
import com.example.servicehi.entity.TicketInvalid;
import com.example.servicehi.service.HotWordService;
import com.example.servicehi.service.ShareTicketImgService;
import com.example.servicehi.service.TicketInvalidService;
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

    private final TicketInvalidService<TicketInvalid> ticketInvalidService;

    private final HotWordService<HotWord> hotWordService;

    private final RedisTemplate redisTemplate;

    /**
     * 默认搜索
     *
     * @return
     */
    @PostMapping(value = "/selectTicket")
    public ResponseUtil selectTicket(@RequestBody ShareTicketImg shareTicketImg) {
        return new ResponseUtil<>(shareTicketImgService.selectTicket(shareTicketImg));
    }

    /**
     * 根据关键词搜索
     * TODO:还没有开发上传图片和标签的关联关系，开发完成后，默认搜索改用这个链接
     *
     * @param shareTicketImg
     * @return
     */
    @PostMapping(value = "/selectQRcodeAndTitle")
    public ResponseUtil selectQRcodeAndTitle(@RequestBody ShareTicketImg shareTicketImg) {
        return new ResponseUtil(shareTicketImgService.selectTitleAndTips(shareTicketImg));
    }

    /**
     * 每次点击复制按钮后，增加一次该优惠券热度
     *
     * @param shareTicketImg
     * @return
     */
    @PostMapping(value = "/addCopyTimes")
    public ResponseUtil addCopyTimes(@RequestBody ShareTicketImg shareTicketImg) {
        shareTicketImgService.updateCopyTimes(shareTicketImg);
        return new ResponseUtil();
    }

    /**
     * 获取热门搜索单词
     * TODO:小程序页面暂时没有实现热词功能，先放着不用
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

    /**
     * 当无效条数增加到一定数量的时候，由后台验证，后删除过期的优惠券置为无效
     * TODO:后台管理页面还未完成，完成后加入到vue页面
     *
     * @param shareTicketImg
     * @return
     */
    @PostMapping(value = "/ticketInvalid")
    public ResponseUtil ticketInvalid(@RequestBody ShareTicketImg shareTicketImg) {
        shareTicketImgService.ticketInvalid(shareTicketImg);
        return new ResponseUtil();
    }

    /**
     * 新增无效优惠券记录
     * TODO:等后台管理页面完成后使用
     *
     * @param ticketInvalid
     * @return
     */
    @PostMapping(value = "/addTicketInvalid")
    public ResponseUtil addTicketInvalid(@RequestBody TicketInvalid ticketInvalid) {
        ticketInvalidService.insert(ticketInvalid);
        return new ResponseUtil();
    }

    /**
     * 查询所有被标记无效的优惠券的次数
     * TODO:等后台管理页面完成后使用
     *
     * @return
     */
    @PostMapping(value = "/ticketInvalidCountList")
    public ResponseUtil ticketInvalidCountList() {
        return new ResponseUtil(ticketInvalidService.selectCountList());
    }

    /**
     * 查询指定优惠券被标记无效的次数
     * TODO:等后台管理页面完成后使用
     *
     * @param ticketInvalid
     * @return
     */
    @PostMapping(value = "/ticketInvalidCount")
    public ResponseUtil ticketInvalidCount(@RequestBody TicketInvalid ticketInvalid) {
        return new ResponseUtil(ticketInvalidService.selectCount(ticketInvalid));
    }

}