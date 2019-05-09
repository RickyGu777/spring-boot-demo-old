package com.example.wechat.dao;

import com.example.wechat.entity.WeChatPublicAccountResponseInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface WeChatPublicAccountResponseInfoDao<T extends WeChatPublicAccountResponseInfo> {
    void insert(T t);

    T selectByRequestInfo(T t);
}
