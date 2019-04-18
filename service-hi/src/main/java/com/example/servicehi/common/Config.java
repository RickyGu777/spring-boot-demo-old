package com.example.servicehi.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Config {
    @Value("${uploadImagePath.windows}")
    private String windows;
    @Value("${uploadImagePath.linux}")
    private String linux;
    @Value("${uploadImagePath.linuxPath}")
    private String linuxPath;
}