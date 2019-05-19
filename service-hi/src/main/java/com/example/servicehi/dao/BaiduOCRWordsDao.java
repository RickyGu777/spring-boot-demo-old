package com.example.servicehi.dao;

import com.example.servicehi.entity.BaiduOCRWords;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaiduOCRWordsDao<T extends BaiduOCRWords> {
    void insert(T t);

    void insertList(List<T> t);

    T selectByUUID(T t);

    List<T> selectByBaiduOCRUUID(T t);
}