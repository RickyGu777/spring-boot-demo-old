package com.example.servicehi.dao;

import com.example.servicehi.entity.ShareTicketImg;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareTicketImgDao<T extends ShareTicketImg> {
    void insert(T t);

    List<T> selectTitleAndTips(T t);

    List<T> selectTicket(T t);

    void ticketInvalid(T t);

    int updateCopyTimes(T t);
}
