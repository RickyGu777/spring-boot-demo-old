package com.example.servicehi.service;

import com.example.servicehi.entity.Diary;

public interface DiaryService<T extends Diary> {
    void insert(T t);
}