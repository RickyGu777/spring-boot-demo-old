package com.example.servicehi.service.impl;

import com.example.servicehi.dao.AhriUrlDao;
import com.example.servicehi.entity.AhriUrl;
import com.example.servicehi.service.AhriUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: KanClad
 * @Date: Created in 2018/5/10 16:41
 */
@Service
public class AhriUrlServiceImpl<T extends AhriUrl> implements AhriUrlService<T> {
    @Autowired
    private AhriUrlDao<T> ahriUrlDao;
    @Override
    public List<T> selectURL(T t) {
        return ahriUrlDao.selectURL(t);
    }

    @Override
    public void insert(T t) {
        t.setCreateDate(new Date());
        t.setModiDate(new Date());
        ahriUrlDao.insert(t);
    }

    @Override
    public List<T> selectTodayURL() {
        return ahriUrlDao.selectTodayURL();
    }

    @Override
    public void delete(T t) {
        ahriUrlDao.delete(t);
    }

    @Override
    public List<T> selectAll() {
        return ahriUrlDao.selectAll();
    }
}