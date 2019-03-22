package com.example.servicehi.service;

import com.example.servicehi.entity.BaiduOCRWords;

public interface BaiduOCRWordsService<T extends BaiduOCRWords> {
    T selectByUUID(T t);
}
