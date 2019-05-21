package com.example.servicehi.service.impl;

import com.example.servicehi.dao.TipsRelationDao;
import com.example.servicehi.entity.TipsRelation;
import com.example.servicehi.entity.dto.DiaryDto;
import com.example.servicehi.service.TipsRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TipsRelationServiceImpl<T extends TipsRelation> implements TipsRelationService<T> {
    @Autowired
    private TipsRelationDao<T> tipsRelationDao;
    @Override
    public void insert(T t) {
        t.setModiDate(new Date());
        tipsRelationDao.insert(t);
    }

    @Override
    public void insertList(List<T> t) {
        tipsRelationDao.insertList(t);
    }

    @Override
    public void deleteByList(DiaryDto t) {
        t.setModiDate(new Date());
        tipsRelationDao.deleteByList(t);
    }

    @Override
    public List<T> selectByNameAndOtherUUID(T t) {
        return tipsRelationDao.selectByNameAndOtherUUID(t);
    }
}
