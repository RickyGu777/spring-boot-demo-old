package com.example.servicehi.dao;

import com.example.servicehi.entity.HotWord;
import org.springframework.stereotype.Repository;

@Repository
public interface HotWordDao<T extends HotWord> {
    void insert(T t);

    void updateTimes(T t);

    T selectHotWord(T t);
}
