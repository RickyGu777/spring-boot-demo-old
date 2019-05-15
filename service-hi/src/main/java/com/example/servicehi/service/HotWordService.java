package com.example.servicehi.service;

import com.example.servicehi.entity.HotWord;

public interface HotWordService<T extends HotWord> {
    void insert(T t);

    T selectHotWord(T t);

    void updateTimes(T t);
}
