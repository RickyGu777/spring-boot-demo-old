package com.example.servicehi.dao;

import com.example.servicehi.entity.Diary;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryDao<T extends Diary> {
    void insert(T t);
}