package com.example.servicehi.service.impl;

import com.example.servicehi.dao.IpRecordDao;
import com.example.servicehi.entity.IpRecord;
import com.example.servicehi.service.IpRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IpRecordServiceImpl<T extends IpRecord> implements IpRecordService<T> {
    @Autowired
    private IpRecordDao<T> ipRecordDao;

    @Override
    public void insert(T t) {
        ipRecordDao.insert(t);
    }

    @Override
    public void insertList(List<T> t) {
        ipRecordDao.insertList(t);
    }
}