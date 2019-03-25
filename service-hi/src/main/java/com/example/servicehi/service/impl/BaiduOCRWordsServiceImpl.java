package com.example.servicehi.service.impl;

import com.example.servicehi.dao.BaiduOCRWordsDao;
import com.example.servicehi.entity.BaiduOCRWords;
import com.example.servicehi.service.BaiduOCRWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaiduOCRWordsServiceImpl<T extends BaiduOCRWords> implements BaiduOCRWordsService<T> {
    @Autowired
    private BaiduOCRWordsDao<T> baiduOCRWordsDao;

    @Override
    public void insert(T t) {
        baiduOCRWordsDao.insert(t);
    }

    @Override
    public void insertList(List<T> t) {
        baiduOCRWordsDao.insertList(t);
    }

    @Override
    public T selectByUUID(T t) {
        return baiduOCRWordsDao.selectByUUID(t);
    }

    @Override
    public List<T> selectByBaiduOCRUUID(T t) {
        return baiduOCRWordsDao.selectByBaiduOCRUUID(t);
    }
}
