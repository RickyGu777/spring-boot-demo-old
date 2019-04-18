package com.example.servicehi.service.impl;

import com.example.servicehi.dao.TipsDao;
import com.example.servicehi.entity.Tips;
import com.example.servicehi.service.TipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TipsServiceImpl<T extends Tips> implements TipsService<T> {
    @Autowired
    private TipsDao<T> tTipsDao;

    @Override
    public void insert(T t) {
        t.setCreateDate(new Date());
        t.setModiDate(t.getCreateDate());
        tTipsDao.insert(t);
    }

    @Override
    public List<T> selectByName(T t) {
        return tTipsDao.selectByName(t);
    }

    @Override
    public T selectByUUID(T t) {
        return tTipsDao.selectByUUID(t);
    }

    @Override
    public List<T> selectTipsType(T t) {
        return tTipsDao.selectTipsType(t);
    }

    @Override
    public T checkRepeat(T t) {
        return tTipsDao.checkRepeat(t);
    }
}
