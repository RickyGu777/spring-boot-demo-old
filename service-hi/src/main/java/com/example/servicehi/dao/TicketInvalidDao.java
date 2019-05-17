package com.example.servicehi.dao;

import com.example.servicehi.entity.TicketInvalid;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketInvalidDao<T extends TicketInvalid> {
    void insert(T t);

    List<T> selectCountList();

    int selectCount(T t);

    void removeTicketInvalid(T t);
}