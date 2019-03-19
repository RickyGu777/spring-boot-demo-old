package com.example.servicehi.dao;

import com.example.servicehi.entity.Joke;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JokeDao <T extends Joke>{

    void insert(T t);

    T selectByTitle(T t);

    List<T> selectJokeList(T t);

    T selectByUUID(T t);
}