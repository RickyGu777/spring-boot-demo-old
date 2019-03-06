package com.example.servicehi.dao;

import com.example.servicehi.entity.Joke;

public interface JokeDao <T extends Joke>{

    void insert(T t);

    T selectByTitle(T t);
}