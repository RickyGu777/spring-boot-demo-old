package com.example.servicehi.dao;

import com.example.servicehi.entity.IpRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IpRecordDao<T extends IpRecord> {
    void insert(T t);

    void insertList(List<T> t);
}