package com.example.servicehi.service;

import com.example.servicehi.entity.BaiduOCRWords;

import java.util.List;

public interface BaiduOCRWordsService<T extends BaiduOCRWords> {
    void insert(T t);

    void insertList(List<T> t);

    T selectByUUID(T t);

    List<T> selectByBaiduOCRUUID(T t);
}
