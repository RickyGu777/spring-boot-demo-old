package com.example.servicehi.service.impl;

import com.example.servicehi.dao.HotWordDao;
import com.example.servicehi.entity.HotWord;
import com.example.servicehi.service.HotWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotWordServiceImpl<T extends HotWord> implements HotWordService<T> {
    @Autowired
    private HotWordDao<T> hotWordDao;

    @Override
    public void insert(T t) {
        hotWordDao.insert(t);
    }

    @Override
    public T selectHotWord(T t) {
        return hotWordDao.selectHotWord(t);
    }

    @Override
    public void updateTimes(T t) {
        hotWordDao.updateTimes(t);
    }
}
