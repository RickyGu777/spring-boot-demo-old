package com.example.servicehi.service;

import com.example.servicehi.entity.DbConfig;

import java.util.List;

public interface DbConfigService<T extends DbConfig> {
    void insert(T t);

    T selectByCode(T t);

    List<T> selectAllConfig();
}