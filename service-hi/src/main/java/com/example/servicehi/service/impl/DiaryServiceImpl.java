package com.example.servicehi.service.impl;

import com.example.servicehi.dao.DiaryDao;
import com.example.servicehi.entity.Diary;
import com.example.servicehi.entity.Tips;
import com.example.servicehi.entity.TipsRelation;
import com.example.servicehi.entity.dto.DiaryDto;
import com.example.servicehi.service.DiaryService;
import com.example.servicehi.service.TipsRelationService;
import com.example.servicehi.service.TipsService;
import com.example.servicehi.util.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DiaryServiceImpl<T extends DiaryDto> implements DiaryService<T> {
    @Autowired
    private DiaryDao<T> diaryDao;

    @Autowired
    private TipsRelationService<TipsRelation> tipsRelationService;

    @Override
    @Transactional
    public void insert(T t) {
        t.setIsDel("0");
        t.setCreateDate(new Date());
        t.setModiDate(new Date());
        diaryDao.insert(t);
        t.getTipsRelations().stream().forEach(item -> {
            item.setUuid(UUIDUtil.createUUID());
            item.setOtherUUID(t.getUuid());
            item.setCreateDate(new Date());
            item.setModiDate(new Date());
        });
        tipsRelationService.insertList(t.getTipsRelations());
    }

    @Override
    public PageInfo selectDiaryList(T t) {
        PageHelper.offsetPage(t.getPage(), t.getSize());
        return new PageInfo<>(diaryDao.selectDiaryList(t));
    }

    @Override
    public T selectByUUID(T t) {
        return diaryDao.selectByUUID(t);
    }

    @Override
    public void updateDiaryByUUID(T t) {
        t.setModiDate(new Date());
        diaryDao.updateDiaryByUUID(t);
        tipsRelationService.deleteByList(t);
    }

    @Override
    public void deleteDiaryByUUID(T t) {
        t.setModiDate(new Date());
        t.setIsDel("1");
        diaryDao.deleteDiaryByUUID(t);
        t.setTipsRelations(null);
        tipsRelationService.deleteByList(t);
    }
}