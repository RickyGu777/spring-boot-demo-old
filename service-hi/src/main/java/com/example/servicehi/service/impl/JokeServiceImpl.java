package com.example.servicehi.service.impl;

import com.example.servicehi.dao.JokeDao;
import com.example.servicehi.entity.Joke;
import com.example.servicehi.service.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JokeServiceImpl<T extends Joke> implements JokeService<T> {
    @Autowired
    private JokeDao<T> jokeDao;

    @Override
    public void insert(T t) {
        jokeDao.insert(t);
    }

    @Override
    public T selectByTitle(T t) {
        return jokeDao.selectByTitle(t);
    }
}