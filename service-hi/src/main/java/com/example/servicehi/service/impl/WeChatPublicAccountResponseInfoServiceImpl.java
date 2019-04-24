package com.example.servicehi.service.impl;

import com.example.servicehi.dao.WeChatPublicAccountResponseInfoDao;
import com.example.servicehi.entity.WeChatPublicAccountResponseInfo;
import com.example.servicehi.service.WeChatPublicAccountResponseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WeChatPublicAccountResponseInfoServiceImpl<T extends WeChatPublicAccountResponseInfo> implements WeChatPublicAccountResponseInfoService<T> {
    @Autowired
    private WeChatPublicAccountResponseInfoDao<T> weChatPublicAccountResponseInfoDao;

    @Override
    public void insert(T t) {
        t.setCreateDate(new Date());
        t.setModiDate(t.getCreateDate());
        weChatPublicAccountResponseInfoDao.insert(t);
    }

    @Override
    public T selectByRequestInfo(T t) {
        return weChatPublicAccountResponseInfoDao.selectByRequestInfo(t);
    }
}
