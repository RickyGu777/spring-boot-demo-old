package com.example.servicehi.service.impl;

import com.example.servicehi.dao.DiaryDao;
import com.example.servicehi.dao.DiaryHistoryDao;
import com.example.servicehi.entity.Diary;
import com.example.servicehi.entity.DiaryHistory;
import com.example.servicehi.entity.Tips;
import com.example.servicehi.entity.TipsRelation;
import com.example.servicehi.entity.dto.DiaryDto;
import com.example.servicehi.service.DiaryHistoryService;
import com.example.servicehi.service.DiaryService;
import com.example.servicehi.service.TipsRelationService;
import com.example.servicehi.service.TipsService;
import com.example.servicehi.util.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DiaryServiceImpl<T extends DiaryDto> implements DiaryService<T> {
    @Autowired
    private DiaryDao<T> diaryDao;

    @Autowired
    private TipsRelationService<TipsRelation> tipsRelationService;

    @Autowired
    private DiaryHistoryService<DiaryHistory> diaryHistoryService;

    @Override
    @Transactional
    public void insert(T t) {
        t.setIsDel("0");
        t.setCreateDate(new Date());
        t.setModiDate(t.getCreateDate());
        diaryDao.insert(t);
        if (!CollectionUtils.isEmpty(t.getTipsRelations())) {
            t.getTipsRelations().stream().forEach(item -> {
                item.setUuid(UUIDUtil.createUUID());
                item.setOtherUUID(t.getUuid());
                item.setModiDate(item.getCreateDate());
            });
            tipsRelationService.insertList(t.getTipsRelations());
        }
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
    @Transactional
    public void updateDiaryByUUID(T t) {
        DiaryHistory diaryHistory = new DiaryHistory();
        diaryHistory.setDiaryUUID(t.getUuid());
        diaryHistory.setTitle(t.getTitle());
        diaryHistory.setHistoryText(diaryDao.selectByUUID(t).getText());
        diaryHistory.setModiIndex(diaryHistoryService.selectCount(diaryHistory) + 1);
        diaryHistory.setCreateDate(new Date());
        diaryHistory.setModiDate(diaryHistory.getCreateDate());
        diaryHistoryService.insert(diaryHistory);
        t.setModiDate(diaryHistory.getCreateDate());
        diaryDao.updateDiaryByUUID(t);
        List<TipsRelation> newTips = new ArrayList<>();
        if (!CollectionUtils.isEmpty(t.getTipsRelations())) {
            t.getTipsRelations().stream().forEach(item -> {
                if (item.getUuid().equals(item.getTipsUUID())) {
                    item.setUuid(UUIDUtil.createUUID());
                    item.setOtherUUID(t.getUuid());
                    item.setModiDate(item.getCreateDate());
                    newTips.add(item);
                }
            });
            tipsRelationService.deleteByList(t);
        }
        if (!CollectionUtils.isEmpty(newTips)) {
            tipsRelationService.insertList(newTips);
        }
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