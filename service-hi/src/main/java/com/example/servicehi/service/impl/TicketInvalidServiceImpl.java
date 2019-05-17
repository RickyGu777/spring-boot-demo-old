package com.example.servicehi.service.impl;

import com.example.servicehi.dao.TicketInvalidDao;
import com.example.servicehi.entity.TicketInvalid;
import com.example.servicehi.service.TicketInvalidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketInvalidServiceImpl<T extends TicketInvalid> implements TicketInvalidService<T> {
    @Autowired
    private TicketInvalidDao<T> tTicketInvalidDao;

    @Override
    public void insert(T t) {
        t.setCreateDate(new Date());
        t.setModiDate(t.getCreateDate());
        tTicketInvalidDao.insert(t);
    }

    @Override
    public List<T> selectCountList() {
        return tTicketInvalidDao.selectCountList();
    }

    @Override
    public int selectCount(T t) {
        return tTicketInvalidDao.selectCount(t);
    }

    @Override
    public void removeTicketInvalid(T t) {
        t.setModiDate(new Date());
        tTicketInvalidDao.removeTicketInvalid(t);
    }
}
