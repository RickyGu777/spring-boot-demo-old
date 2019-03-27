package com.example.servicehi.dao;

import com.example.servicehi.entity.Tips;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipsDao<T extends Tips> {
    void insert(T t);

    List<T> selectByName(T t);

    T selectByUUID(T t);
}