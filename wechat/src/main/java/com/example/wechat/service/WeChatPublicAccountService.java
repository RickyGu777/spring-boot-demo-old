package com.example.wechat.service;

import com.example.wechat.entity.WeChatPublicAccount;

public interface WeChatPublicAccountService<T extends WeChatPublicAccount> {
    void insert(T t);
}