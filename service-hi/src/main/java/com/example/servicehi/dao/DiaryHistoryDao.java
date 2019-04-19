package com.example.servicehi.dao;

import com.example.servicehi.entity.DiaryHistory;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryHistoryDao<T extends DiaryHistory> {
    void insert(T t);

    Integer selectCount(T t);
}
