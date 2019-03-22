package com.example.servicehi.service.impl;

import com.example.servicehi.dao.BaiduOCRWordsDao;
import com.example.servicehi.entity.BaiduOCRWords;
import com.example.servicehi.service.BaiduOCRWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaiduOCRWordsServiceImpl<T extends BaiduOCRWords> implements BaiduOCRWordsService<T> {
    @Autowired
    private BaiduOCRWordsDao<T> baiduOCRWordsDao;

    @Override
    public T selectByUUID(T t) {
        return baiduOCRWordsDao.selectByUUID(t);
    }
}
