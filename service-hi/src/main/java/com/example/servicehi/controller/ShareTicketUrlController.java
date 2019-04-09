package com.example.servicehi.controller;

import com.example.servicehi.entity.ShareTicketUrl;
import com.example.servicehi.entity.dto.ShareTicketUrlDto;
import com.example.servicehi.service.ShareTicketUrlService;
import com.example.servicehi.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.Validate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/ShareTicketUrl")
@RestController
@AllArgsConstructor
@Slf4j
public class ShareTicketUrlController {
    private final ShareTicketUrlService<ShareTicketUrl> shareTicketUrlService;

    /**
     * 新增优惠券链接
     *
     * @param shareTicketUrl
     * @return
     */
    @PostMapping(value = "/insert")
    public ResponseUtil insert(@RequestBody ShareTicketUrl shareTicketUrl) {
        if (CollectionUtils.isEmpty(shareTicketUrlService.selectRepeatUrl(shareTicketUrl))) {
            shareTicketUrlService.insert(shareTicketUrl);
            return new ResponseUtil();
        } else {
            return ResponseUtil.buildERROR("已有有重复链接");
        }
    }

    /**
     * 批量新增优惠券链接
     *
     * @param shareTicketUrls
     * @return
     */
    @PostMapping(value = "/insertList")
    public ResponseUtil insertList(@RequestBody List<ShareTicketUrl> shareTicketUrls) {
        shareTicketUrls.forEach(item -> Validate.notEmpty(item.getUrl()));
        List<ShareTicketUrl> repeatUrlList = shareTicketUrlService.selectRepeatUrlList(shareTicketUrls);
        List<ShareTicketUrl> newList = new ArrayList<>();
        List<ShareTicketUrl> oldList = new ArrayList<>();
        shareTicketUrls.forEach(item -> {
            if (repeatUrlList.contains(item)) {
                oldList.add(item);
            } else {
                item.setIsDel("0");
                newList.add(item);
            }
        });
        if (!CollectionUtils.isEmpty(newList)) {
            shareTicketUrlService.insertList(newList);
        }
        return new ResponseUtil(oldList);
    }

    @PostMapping(value = "/changeTicketStatus")
    public ResponseUtil changeTicketStatus(@RequestBody List<ShareTicketUrl> shareTicketUrls) {
        shareTicketUrls.forEach(item -> Validate.notEmpty(item.getStatus()));
        shareTicketUrlService.updateListStatus(shareTicketUrls);
        return new ResponseUtil();
    }

    /**
     * 根据类型查询，如果有标题则同时查询
     *
     * @param shareTicketUrlDto
     * @return
     */
    @PostMapping(value = "/selectTitleAndTipsName")
    public ResponseUtil selectTitleAndTipsName(@RequestBody ShareTicketUrlDto shareTicketUrlDto) {
        return new ResponseUtil(shareTicketUrlService.selectTitleAndTipsName(shareTicketUrlDto));
    }

    /**
     * 查询重复url
     * 不重复返回true，重复返回false
     *
     * @param shareTicketUrl
     * @return
     */
    @PostMapping(value = "/selectRepeatUrl")
    public ResponseUtil selectRepeatUrl(@RequestBody ShareTicketUrl shareTicketUrl) {
        return new ResponseUtil(CollectionUtils.isEmpty(shareTicketUrlService.selectRepeatUrl(shareTicketUrl)));
    }
}
