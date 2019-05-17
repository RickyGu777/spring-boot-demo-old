package com.example.servicehi.common;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
@Slf4j
public class Config {
    @Value("${uploadImagePath.windows}")
    private String windows;
    @Value("${uploadImagePath.linux}")
    private String linux;
    @Value("${uploadImagePath.linuxPath}")
    private String linuxPath;
    @Value("${wechat.mp-app-id}")
    private String weChatAppId;
    @Value("${wechat.mp-app-secret}")
    private String weChatAppSecret;
    @Value("${wechat.token}")
    private String weChatToken;
    @Setter
    private String filePath;
}