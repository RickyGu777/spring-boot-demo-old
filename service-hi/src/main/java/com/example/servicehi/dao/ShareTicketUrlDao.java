package com.example.servicehi.dao;

import com.example.servicehi.entity.ShareTicketUrl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareTicketUrlDao<T extends ShareTicketUrl> {
    void insert(T t);

    List<T> selectTitleAndTipsName(T t);

    List<T> selectRepeatUrl(T t);
}
