package com.example.servicehi.service;

import com.example.servicehi.entity.IpAddress;

public interface IpAddressService<T extends IpAddress> {
    T selectMac(T t);

    void updateIP();

    void insert(T t);

    void delete();
}