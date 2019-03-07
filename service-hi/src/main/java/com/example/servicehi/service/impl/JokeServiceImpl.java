package com.example.servicehi.service.impl;

import com.example.servicehi.dao.JokeDao;
import com.example.servicehi.entity.Joke;
import com.example.servicehi.service.JokeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JokeServiceImpl<T extends Joke> implements JokeService<T> {
    @Autowired
    private JokeDao<T> jokeDao;

    @Override
    public void insert(T t) {
        t.setIsDel("0");
        t.setCreateDate(new Date());
        t.setModiDate(new Date());
        jokeDao.insert(t);
    }

    @Override
    public T selectByTitle(T t) {
        return jokeDao.selectByTitle(t);
    }

    @Override
    public PageInfo selectJokeList(T t) {
        PageHelper.startPage(t.getPage(), t.getSize());
        return new PageInfo<>(jokeDao.selectJokeList(t));
    }
}