package com.example.servicehi.service;

import com.example.servicehi.entity.ShareTicketImg;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ShareTicketImgService<T extends ShareTicketImg> {
    void insert(T t);

    List<T> selectTitleAndTips(T t);
}
