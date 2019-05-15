package com.example.servicehi.dao;

import com.example.servicehi.entity.HotWord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotWordDao<T extends HotWord> {
    void insert(T t);

    void updateTimes(T t);

    T selectHotWord(T t);

    List<T> selectTodayHotWord();

    List<T> selectDateHotWord(T t);
}
