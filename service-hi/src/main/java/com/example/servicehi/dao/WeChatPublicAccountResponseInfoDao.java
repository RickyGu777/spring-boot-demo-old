package com.example.servicehi.dao;

import com.example.servicehi.entity.WeChatPublicAccountResponseInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface WeChatPublicAccountResponseInfoDao<T extends WeChatPublicAccountResponseInfo> {
    void insert(T t);

    T selectByRequestInfo(T t);
}
