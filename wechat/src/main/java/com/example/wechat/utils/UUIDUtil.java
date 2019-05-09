package com.example.wechat.utils;

import java.util.UUID;

public class UUIDUtil {
    public static String createUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}