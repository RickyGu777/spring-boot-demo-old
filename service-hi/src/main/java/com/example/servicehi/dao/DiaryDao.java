package com.example.servicehi.dao;

import com.example.servicehi.entity.Diary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryDao<T extends Diary> {
    void insert(T t);

    List<T> selectDiaryList(T t);

    T selectByUUID(T t);
}