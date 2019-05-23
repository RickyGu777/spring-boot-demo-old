package com.example.servicehi.service;

import com.example.servicehi.entity.IpRecord;

import java.util.List;

public interface IpRecordService<T extends IpRecord> {
    void insert(T t);

    void insertList(List<T> t);
}
