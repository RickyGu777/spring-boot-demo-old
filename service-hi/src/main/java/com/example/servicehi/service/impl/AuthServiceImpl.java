package com.example.servicehi.service.impl;

import com.example.servicehi.dao.AuthDao;
import com.example.servicehi.entity.Auth;
import com.example.servicehi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl<T extends Auth> implements AuthService<T> {
    @Autowired
    private AuthDao<T> authDao;

    @Override
    public void insert(T t) {
        authDao.insert(t);
    }

    @Override
    public List<T> selectAll() {
        return authDao.selectAll();
    }

    @Override
    public T selectByUrl(T t) {
        return authDao.selectByUrl(t);
    }

    @Override
    public void delete(T t) {
        authDao.delete(t);
    }

    @Override
    public void updateAuthName(T t) {
        authDao.updateAuthName(t);
    }

    @Override
    public List<T> selectAuthTree(T t) {
        return authDao.selectAuthTree(t);
    }
}
