package com.example.servicehi.service.impl;

import com.example.servicehi.dao.UserDao;
import com.example.servicehi.entity.User;
import com.example.servicehi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl<T extends User> implements UserService<T> {
    @Autowired
    private UserDao<T> userDao;
}