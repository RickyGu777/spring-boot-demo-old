package com.example.wechat.service;

import com.example.wechat.entity.WeChatPublicAccountResponseInfo;

public interface WeChatPublicAccountResponseInfoService<T extends WeChatPublicAccountResponseInfo> {
    void insert(T t);

    T selectByRequestInfo(T t);
}
