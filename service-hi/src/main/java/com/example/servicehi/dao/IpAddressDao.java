package com.example.servicehi.dao;

import com.example.servicehi.entity.IpAddress;
import org.springframework.stereotype.Repository;

@Repository
public interface IpAddressDao<T extends IpAddress> {
    T selectIP(T t);

    void updateIP();

    void insert(T t);

    void delete();
}