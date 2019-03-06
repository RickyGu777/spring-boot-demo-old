package com.example.servicehi.service;

import com.example.servicehi.entity.Joke;

public interface JokeService<T extends Joke> {

    void insert(T t);

    T selectByTitle(T t);
}