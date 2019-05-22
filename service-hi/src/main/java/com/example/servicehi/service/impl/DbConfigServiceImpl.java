package com.example.servicehi.service.impl;

import com.example.servicehi.dao.DbConfigDao;
import com.example.servicehi.entity.DbConfig;
import com.example.servicehi.service.DbConfigService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DbConfigServiceImpl<T extends DbConfig> implements DbConfigService<T> {
    @Autowired
    private DbConfigDao<T> dbConfigDao;

    @Override
    public void insert(T t) {
        t.setModiDate(new Date());
        t.setCreateDate(t.getModiDate());
        dbConfigDao.insert(t);
    }

    @Override
    public T selectByCode(T t) {
        Validate.isTrue(StringUtils.isNotEmpty(t.getCode()),"查询配置CODE不能为空，请检查");
        return dbConfigDao.selectByCode(t);
    }

    @Override
    public List<T> selectAllConfig() {
        return dbConfigDao.selectAllConfig();
    }
}