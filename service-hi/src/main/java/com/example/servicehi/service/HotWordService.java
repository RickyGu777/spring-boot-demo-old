package com.example.servicehi.service;

import com.example.servicehi.entity.HotWord;

import java.util.List;

public interface HotWordService<T extends HotWord> {
    void insert(T t);

    T selectHotWord(T t);

    void updateTimes(T t);

    List<T> selectTodayHotWord();

    List<T> selectDateHotWord(T t);
}
