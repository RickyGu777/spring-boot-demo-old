package com.example.servicehi.service.impl;

import com.example.servicehi.dao.DiaryHistoryDao;
import com.example.servicehi.entity.DiaryHistory;
import com.example.servicehi.service.DiaryHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DiaryHistoryServiceImpl<T extends DiaryHistory> implements DiaryHistoryService<T> {
    @Autowired
    private DiaryHistoryDao<T> diaryHistoryDao;

    @Override
    public void insert(T t) {
        t.setModiDate(new Date());
        t.setCreateDate(t.getModiDate());
        diaryHistoryDao.insert(t);
    }

    @Override
    public Integer selectCount(T t) {
        return diaryHistoryDao.selectCount(t);
    }
}
