package com.example.oauth.service;

import com.example.oauth.entity.User;

import java.util.List;

public interface UserService<T extends User> {
    List<T> selectByName();
}