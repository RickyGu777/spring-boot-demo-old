package com.example.servicehi.service.impl;

import com.example.servicehi.dao.WeChatPublicAccountDao;
import com.example.servicehi.entity.WeChatPublicAccount;
import com.example.servicehi.service.WeChatPublicAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WeChatPublicAccountServiceImpl<T extends WeChatPublicAccount> implements WeChatPublicAccountService<T> {
    @Autowired
    private WeChatPublicAccountDao<T> weChatPublicAccountDao;

    @Override
    public void insert(T t) {
        t.setCreateDate(new Date());
        t.setModiDate(t.getCreateDate());
        weChatPublicAccountDao.insert(t);
    }
}
