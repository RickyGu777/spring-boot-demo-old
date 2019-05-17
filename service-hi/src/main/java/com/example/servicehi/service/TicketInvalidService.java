package com.example.servicehi.service;

import com.example.servicehi.entity.TicketInvalid;

import java.util.List;

public interface TicketInvalidService<T extends TicketInvalid> {
    void insert(T t);

    List<T> selectCountList();

    int selectCount(T t);

    void removeTicketInvalid(T t);
}