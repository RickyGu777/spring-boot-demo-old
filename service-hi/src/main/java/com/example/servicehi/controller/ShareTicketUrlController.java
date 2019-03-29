package com.example.servicehi.controller;

import com.example.servicehi.dao.ShareTicketUrlDao;
import com.example.servicehi.entity.ShareTicketUrl;
import com.example.servicehi.entity.dto.ShareTicketUrlDto;
import com.example.servicehi.service.ShareTicketUrlService;
import com.example.servicehi.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/ShareTicketUrl")
@RestController
@AllArgsConstructor
@Slf4j
public class ShareTicketUrlController {
    private final ShareTicketUrlService<ShareTicketUrl> shareTicketUrlService;

    @PostMapping(value = "/insert")
    public ResponseUtil insert(@RequestBody ShareTicketUrl shareTicketUrl) {
        if (CollectionUtils.isEmpty(shareTicketUrlService.selectRepeatUrl(shareTicketUrl))) {
            shareTicketUrlService.insert(shareTicketUrl);
            return new ResponseUtil();
        } else {
            return ResponseUtil.buildERROR("已有有重复URL");
        }
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
