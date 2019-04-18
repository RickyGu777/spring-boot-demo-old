package com.example.servicehi.service.impl;

import com.example.servicehi.dao.ShareTicketImgDao;
import com.example.servicehi.entity.ShareTicketImg;
import com.example.servicehi.service.ShareTicketImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ShareTicketImgServiceImpl<T extends ShareTicketImg> implements ShareTicketImgService<T>{
    @Autowired
    private ShareTicketImgDao<T> shareTicketImgDao;

    @Override
    public void insert(T t) {
        t.setCreateDate(new Date());
        t.setModiDate(t.getCreateDate());
        shareTicketImgDao.insert(t);
    }
}
