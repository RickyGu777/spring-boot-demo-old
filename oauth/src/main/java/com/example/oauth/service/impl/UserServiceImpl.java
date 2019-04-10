package com.example.oauth.service.impl;

import com.example.oauth.dao.UserDao;
import com.example.oauth.entity.User;
import com.example.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl<T extends User> implements UserService<T> {
    @Autowired
    private UserDao<T> userDao;

    @Override
    public List<T> selectByName() {
        return userDao.selectByName();
    }
}