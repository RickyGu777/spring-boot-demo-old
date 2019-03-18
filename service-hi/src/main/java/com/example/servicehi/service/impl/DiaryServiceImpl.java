package com.example.servicehi.service.impl;

import com.example.servicehi.dao.DiaryDao;
import com.example.servicehi.entity.Diary;
import com.example.servicehi.service.DiaryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DiaryServiceImpl<T extends Diary> implements DiaryService<T> {
    @Autowired
    private DiaryDao<T> diaryDao;

    @Override
    public void insert(T t) {
        t.setIsDel("0");
        t.setCreateDate(new Date());
        t.setModiDate(new Date());
        diaryDao.insert(t);
    }

    @Override
    public PageInfo selectDiaryList(T t) {
        PageHelper.offsetPage(t.getPage(), t.getSize());
        return new PageInfo<>(diaryDao.selectDiaryList(t));
    }
}