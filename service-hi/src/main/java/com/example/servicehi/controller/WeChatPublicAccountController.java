package com.example.servicehi.controller;

import com.example.servicehi.entity.WeChatPublicAccount;
import com.example.servicehi.entity.WeChatPublicAccountResponseInfo;
import com.example.servicehi.service.WeChatPublicAccountResponseInfoService;
import com.example.servicehi.service.WeChatPublicAccountService;
import com.example.servicehi.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/Tips")
@RestController
@AllArgsConstructor
@Slf4j
public class WeChatPublicAccountController {
    private final WeChatPublicAccountService<WeChatPublicAccount> weChatPublicAccountService;

    private final WeChatPublicAccountResponseInfoService<WeChatPublicAccountResponseInfo> weChatPublicAccountResponseInfoService;

    @PostMapping(value = "/addResponseInfo")
    public ResponseUtil addResponseInfo(@RequestBody WeChatPublicAccountResponseInfo weChatPublicAccountResponseInfo) {
        weChatPublicAccountResponseInfoService.insert(weChatPublicAccountResponseInfo);
        return new ResponseUtil();
    }

    @PostMapping(value = "/selectMessageByOpenid")
    public ResponseUtil selectMessageByOpenid(@RequestBody WeChatPublicAccount weChatPublicAccount) {
        return new ResponseUtil();
    }
}
