package com.example.servicehi.service;

import com.example.servicehi.entity.Auth;

import java.util.List;

public interface AuthService<T extends Auth> {
    void insert(T t);

    List<T> selectAll();

    T selectByUrl(T t);

    void delete(T t);
}
