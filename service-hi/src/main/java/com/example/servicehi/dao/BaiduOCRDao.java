package com.example.servicehi.dao;

import com.example.servicehi.entity.BaiduOCR;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaiduOCRDao<T extends BaiduOCR> {
    void insert(T t);

    T selectByUUID(T t);
}