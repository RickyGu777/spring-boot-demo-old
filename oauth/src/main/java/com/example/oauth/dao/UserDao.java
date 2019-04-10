package com.example.oauth.dao;

import com.example.oauth.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao<T extends User> {
    List<T> selectByName();
}