package com.example.servicehi.dao;

import com.example.servicehi.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao<T extends User> {
}