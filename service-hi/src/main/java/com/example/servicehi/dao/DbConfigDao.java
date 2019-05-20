package com.example.servicehi.dao;

import com.example.servicehi.entity.DbConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DbConfigDao<T extends DbConfig> {
    void insert(T t);

    T selectByCode(T t);

    List<T> selectAllConfig();
}
