package com.example.servicehi.service;

import com.example.servicehi.entity.Auth;
import com.example.servicehi.entity.BaiduOCR;

import java.util.List;

public interface BaiduOCRService<T extends BaiduOCR> {
    T selectByUUID(T t);
}
