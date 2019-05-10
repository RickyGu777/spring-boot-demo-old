package com.example.wechat.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/wx/user/{appid}")
public class WxUserController {
    @Autowired
    private WxMpService wxService;

    @PostMapping("/getUserInfo")
    public String user(@PathVariable String appid) throws WxErrorException {
        this.wxService.switchover(appid);
        return wxService.getAccessToken();
    }
}
