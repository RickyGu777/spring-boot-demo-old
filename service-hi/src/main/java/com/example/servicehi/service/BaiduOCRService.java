package com.example.servicehi.service;

import com.example.servicehi.entity.Auth;
import com.example.servicehi.entity.BaiduOCR;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BaiduOCRService<T extends BaiduOCR> {
    void insert(T t);

    T selectByUUID(T t);

    PageInfo selectOCRList(T t);
}
