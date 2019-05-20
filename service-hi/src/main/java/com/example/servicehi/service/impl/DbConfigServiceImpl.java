package com.example.servicehi.service.impl;

import com.example.servicehi.dao.DbConfigDao;
import com.example.servicehi.entity.DbConfig;
import com.example.servicehi.service.DbConfigService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbConfigServiceImpl<T extends DbConfig> implements DbConfigService<T> {
    @Autowired
    private DbConfigDao<T> dbConfigDao;

    @Override
    public void insert(T t) {
        dbConfigDao.insert(t);
    }

    @Override
    public T selectByCode(T t) {
        Validate.isTrue(StringUtils.isNotEmpty(t.getCode()),"错误");
        return dbConfigDao.selectByCode(t);
    }

    @Override
    public List<T> selectAllConfig() {
        return dbConfigDao.selectAllConfig();
    }
}