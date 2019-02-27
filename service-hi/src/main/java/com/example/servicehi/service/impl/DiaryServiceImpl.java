package com.example.servicehi.service.impl;

import com.example.servicehi.dao.DiaryDao;
import com.example.servicehi.entity.Diary;
import com.example.servicehi.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryServiceImpl<T extends Diary> implements DiaryService<T> {
    @Autowired
    private DiaryDao<T> diaryDao;

    @Override
    public void insert(T t) {
        diaryDao.insert(t);
    }
}