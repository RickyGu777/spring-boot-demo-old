package com.example.wechat.dao;

import com.example.wechat.entity.WeChatPublicAccount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeChatPublicAccountDao<T extends WeChatPublicAccount> {
    void insert(T t);

    List<T> selectMessage(T t);
}