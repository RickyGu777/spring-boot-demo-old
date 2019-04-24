package com.example.servicehi.service;

import com.example.servicehi.entity.WeChatPublicAccount;

public interface WeChatPublicAccountService<T extends WeChatPublicAccount> {
    void insert(T t);
}