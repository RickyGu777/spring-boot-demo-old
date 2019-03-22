package com.example.servicehi.service.impl;

import com.example.servicehi.dao.BaiduOCRDao;
import com.example.servicehi.entity.BaiduOCR;
import com.example.servicehi.service.BaiduOCRService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaiduOCRServiceImpl<T extends BaiduOCR> implements BaiduOCRService<T> {
    @Autowired
    private BaiduOCRDao<T> baiduOCRDao;
    @Override
    public T selectByUUID(T t) {
        return baiduOCRDao.selectByUUID(t);
    }
}
