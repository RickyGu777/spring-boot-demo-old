package com.example.servicehi.service;

import com.example.servicehi.entity.Diary;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DiaryService<T extends Diary> {
    void insert(T t);

    PageInfo selectDiaryList(T t);

    T selectByUUID(T t);

    void updateDiaryByUUID(T t);

    void deleteDiaryByUUID(T t);
}