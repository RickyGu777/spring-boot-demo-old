package com.example.servicehi.service;

import com.example.servicehi.entity.Joke;
import com.github.pagehelper.PageInfo;

public interface JokeService<T extends Joke> {

    void insert(T t);

    T selectByTitle(T t);

    PageInfo selectJokeList(T t);
}