package com.example.servicehi.dao;

import com.example.servicehi.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao<T extends Menu> {
    List<T> selectMenu(T t);

    void insert(T t);
}