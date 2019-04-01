package com.example.servicehi.service.impl;

import com.example.servicehi.dao.ShareTicketUrlDao;
import com.example.servicehi.entity.ShareTicketUrl;
import com.example.servicehi.service.ShareTicketUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareTicketUrlServiceImpl<T extends ShareTicketUrl> implements ShareTicketUrlService<T> {
    @Autowired
    private ShareTicketUrlDao<T> shareTicketUrlDao;

    @Override
    public void insert(T t) {
        shareTicketUrlDao.insert(t);
    }

    @Override
    public void insertList(List<T> t) {
        shareTicketUrlDao.insertList(t);
    }

    @Override
    public List<T> selectTitleAndTipsName(T t) {
        return shareTicketUrlDao.selectTitleAndTipsName(t);
    }

    @Override
    public List<T> selectRepeatUrl(T t) {
        return shareTicketUrlDao.selectRepeatUrl(t);
    }

    @Override
    public List<T> selectRepeatUrlList(List<T> t) {
        return shareTicketUrlDao.selectRepeatUrlList(t);
    }
}
