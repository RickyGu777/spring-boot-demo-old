package com.example.servicehi.service;

import com.example.servicehi.entity.DiaryHistory;

public interface DiaryHistoryService<T extends DiaryHistory> {
    void insert(T t);

    Integer selectCount(T t);
}
