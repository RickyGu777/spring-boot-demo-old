package com.example.servicehi.dao;

import com.example.servicehi.entity.Auth;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthDao<T extends Auth> {
    void insert(T t);

    List<T> selectAll();

    T selectByUrl(T t);

    void delete(T t);
}

