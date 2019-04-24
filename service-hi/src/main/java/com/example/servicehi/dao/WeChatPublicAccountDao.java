package com.example.servicehi.dao;

import com.example.servicehi.entity.WeChatPublicAccount;
import org.springframework.stereotype.Repository;

@Repository
public interface WeChatPublicAccountDao<T extends WeChatPublicAccount> {
    void insert(T t);
}