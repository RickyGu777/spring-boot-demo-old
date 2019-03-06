package com.example.servicehi.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
public class Config {
    @Value("${uploadImagePath.windows}")
    private String windows;
    @Value("${uploadImagePath.linux}")
    private String linux;
}