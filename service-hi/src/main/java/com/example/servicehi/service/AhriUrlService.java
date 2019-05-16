package com.example.servicehi.service;

import com.example.servicehi.entity.AhriUrl;
import org.apache.ibatis.datasource.DataSourceException;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AhriUrlService<T extends AhriUrl> {
    List<T> selectURL(T t);

    void insert(T t) throws Exception;

    List<T> selectTodayURL();

    void delete(T t);

    List<T> selectAll();
}
