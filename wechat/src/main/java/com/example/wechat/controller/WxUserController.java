package com.example.wechat.controller;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wx/user/{appid}")
public class WxUserController {
    @Autowired
    private WxMpService wxService;

    @PostMapping(value = "/")
    public String user(@PathVariable String appid) throws WxErrorException {
        this.wxService.switchover(appid);
        return wxService.getAccessToken();
    }
}
