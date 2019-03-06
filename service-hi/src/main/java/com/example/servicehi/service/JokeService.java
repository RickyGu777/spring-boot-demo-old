package com.example.servicehi.service;

import com.example.servicehi.entity.Joke;

import java.util.List;

public interface JokeService<T extends Joke> {

    void insert(T t);

    T selectByTitle(T t);

    List<T> selectJokeList(T t);
}