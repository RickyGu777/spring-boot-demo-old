package com.example.servicehi.service;

import com.example.servicehi.entity.Tips;

import java.util.List;

public interface TipsService <T extends Tips> {
    void insert(T t);

    List<T> selectByName(T t);

    T selectByUUID(T t);

    List<T> selectTipsType(T t);
}
