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
//    private final ShareTicketUrlService<ShareTicketUrl> shareTicketUrlService;

    private final ShareTicketImgService<ShareTicketImg> shareTicketImgService;

    private final HotWordService<HotWord> hotWordService;

    private final RedisTemplate redisTemplate;
//    /**
//     * 新增优惠券链接
//     *
//     * @param shareTicketUrl
//     * @return
//     */
//    @PostMapping(value = "/insert")
//    public ResponseUtil insert(@RequestBody ShareTicketUrl shareTicketUrl) {
//        if (CollectionUtils.isEmpty(shareTicketUrlService.selectRepeatUrl(shareTicketUrl))) {
//            shareTicketUrlService.insert(shareTicketUrl);
//            return new ResponseUtil();
//        } else {
//            return ResponseUtil.buildERROR("已有有重复链接");
//        }
//    }
//
//    /**
//     * 批量新增优惠券链接
//     *
//     * @param shareTicketUrls
//     * @return
//     */
//    @PostMapping(value = "/insertList")
//    public ResponseUtil insertList(@RequestBody List<ShareTicketUrl> shareTicketUrls) {
//        shareTicketUrls.forEach(item -> Validate.notEmpty(item.getUrl()));
//        List<ShareTicketUrl> repeatUrlList = shareTicketUrlService.selectRepeatUrlList(shareTicketUrls);
//        List<ShareTicketUrl> newList = new ArrayList<>();
//        List<ShareTicketUrl> oldList = new ArrayList<>();
//        shareTicketUrls.forEach(item -> {
//            if (repeatUrlList.contains(item)) {
//                oldList.add(item);
//            } else {
//                item.setIsDel("0");
//                newList.add(item);
//            }
//        });
//        if (!CollectionUtils.isEmpty(newList)) {
//            shareTicketUrlService.insertList(newList);
//        }
//        return new ResponseUtil(oldList);
//    }
//
//    /**
//     * 修改状态
//     *
//     * @param shareTicketUrls
//     * @return
//     */
//    @PostMapping(value = "/changeTicketStatus")
//    public ResponseUtil changeTicketStatus(@RequestBody List<ShareTicketUrl> shareTicketUrls) {
//        shareTicketUrls.forEach(item -> Validate.notEmpty(item.getStatus()));
//        shareTicketUrlService.updateListStatus(shareTicketUrls);
//        return new ResponseUtil();
//    }
//
//    /**
//     * 根据类型查询，如果有标题则同时查询
//     *
//     * @param shareTicketUrlDto
//     * @return
//     */
//    @PostMapping(value = "/selectTitleAndTipsName")
//    public ResponseUtil selectTitleAndTipsName(@RequestBody ShareTicketUrlDto shareTicketUrlDto) {
//        return new ResponseUtil(shareTicketUrlService.selectTitleAndTipsName(shareTicketUrlDto));
//    }
//
//    /**
//     * 查询重复url
//     * 不重复返回true，重复返回false
//     *
//     * @param shareTicketUrl
//     * @return
//     */
//    @PostMapping(value = "/selectRepeatUrl")
//    public ResponseUtil selectRepeatUrl(@RequestBody ShareTicketUrl shareTicketUrl) {
//        return new ResponseUtil(CollectionUtils.isEmpty(shareTicketUrlService.selectRepeatUrl(shareTicketUrl)));
//    }

    @PostMapping(value = "/selectQRcodeAndTitle")
    public ResponseUtil selectQRcodeAndTitle(@RequestBody ShareTicketImg shareTicketImg) {
        return new ResponseUtil(shareTicketImgService.selectTitleAndTips(shareTicketImg));
    }

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