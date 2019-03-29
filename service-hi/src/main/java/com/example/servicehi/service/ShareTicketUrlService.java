package com.example.servicehi.service;

import com.example.servicehi.entity.ShareTicketUrl;

import java.util.List;

public interface ShareTicketUrlService<T extends ShareTicketUrl> {
    void insert(T t);

    List<T> selectTitleAndTipsName(T t);

    List<T> selectRepeatUrl(T t);
}
