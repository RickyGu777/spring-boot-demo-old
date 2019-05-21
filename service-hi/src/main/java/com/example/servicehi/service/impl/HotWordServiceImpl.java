package com.example.servicehi.service.impl;

import com.example.servicehi.dao.HotWordDao;
import com.example.servicehi.entity.HotWord;
import com.example.servicehi.service.HotWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HotWordServiceImpl<T extends HotWord> implements HotWordService<T> {
    @Autowired
    private HotWordDao<T> hotWordDao;

    @Override
    public void insert(T t) {
        t.setModiDate(new Date());
        t.setCreateDate(t.getModiDate());
        hotWordDao.insert(t);
    }

    @Override
    public T selectHotWord(T t) {
        return hotWordDao.selectHotWord(t);
    }

    @Override
    public void updateTimes(T t) {
        t.setModiDate(new Date());
        hotWordDao.updateTimes(t);
    }

    @Override
    public List<T> selectTodayHotWord() {
        return hotWordDao.selectTodayHotWord();
    }

    @Override
    public List<T> selectDateHotWord(T t) {
        return hotWordDao.selectDateHotWord(t);
    }
}
