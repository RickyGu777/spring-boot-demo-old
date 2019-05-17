package com.example.servicehi.service;

import com.example.servicehi.entity.ShareTicketImg;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ShareTicketImgService<T extends ShareTicketImg> {
    void insert(T t);

    List<T> selectTitleAndTips(T t);

    List<T> selectTicket(T t);

    void ticketInvalid(T t);
}
