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
    // 这个是初始化时判断系统类型后设置的路径
    @Setter
    private String filePath;
    // 这个是TXT中读取到的实际QQ图片存放的路径
    @Setter
    private String QQFilePath;
    // 这个是TXT文件路径
    @Value("${uploadImagePath.readQQFilePath}")
    private String readQQFilePath;
    @Value("${uploadImagePath.backUpFilePath}")
    private String backUpFilePath;
}