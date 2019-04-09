package com.example.servicehi.service;

import com.example.servicehi.entity.ShareTicketImg;
import org.springframework.stereotype.Repository;

public interface ShareTicketImgService<T extends ShareTicketImg> {
    void insert(T t);
}
