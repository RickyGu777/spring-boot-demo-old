package com.example.servicehi.service.impl;

import com.example.servicehi.dao.BaiduOCRDao;
import com.example.servicehi.entity.BaiduOCR;
import com.example.servicehi.service.BaiduOCRService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BaiduOCRServiceImpl<T extends BaiduOCR> implements BaiduOCRService<T> {
    @Autowired
    private BaiduOCRDao<T> baiduOCRDao;

    @Override
    public void insert(T t) {
        t.setIsDel("0");
        t.setCreateDate(new Date());
        t.setModiDate(new Date());
        baiduOCRDao.insert(t);
    }

    @Override
    public T selectByUUID(T t) {
        return baiduOCRDao.selectByUUID(t);
    }

    @Override
    public List<T> selectOCRList(T t) {
        return baiduOCRDao.selectOCRList(t);
    }
}
