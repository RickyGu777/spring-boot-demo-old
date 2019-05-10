package com.example.wechat.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/wx/user/{appid}")
public class WxUserController {
    @Autowired
    private WxMpService wxService;

    @PostMapping("/getUserInfo")
    public Object user(@PathVariable String appid, @RequestBody(required = false) String openId) throws WxErrorException {
        Validate.isTrue(StringUtils.isEmpty(openId),"openId不能为空");
        this.wxService.switchover(appid);
        WxMpUserService userService = wxService.getUserService();
        WxMpUser wxMpUser = userService.userInfo(openId);
        return wxMpUser;
    }
}
