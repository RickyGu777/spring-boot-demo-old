package com.example.servicehi.service;

import com.example.servicehi.entity.WeChatPublicAccountResponseInfo;
import org.springframework.stereotype.Repository;

public interface WeChatPublicAccountResponseInfoService<T extends WeChatPublicAccountResponseInfo> {
    void insert(T t);

    T selectByRequestInfo(T t);
}
