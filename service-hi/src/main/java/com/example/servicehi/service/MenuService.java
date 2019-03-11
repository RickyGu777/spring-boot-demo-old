package com.example.servicehi.service;

import com.example.servicehi.entity.Menu;

import java.util.List;

public interface MenuService<T extends Menu> {
    List<T> selectMenu(T t);
}