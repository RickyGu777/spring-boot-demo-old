package com.example.servicehi.service.impl;

import com.example.servicehi.dao.UserAuthRelationDao;
import com.example.servicehi.entity.UserAuthRelation;
import com.example.servicehi.service.UserAuthRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthRelationServiceImpl<T extends UserAuthRelation> implements UserAuthRelationService<T> {
    @Autowired
    private UserAuthRelationDao<T> userAuthRelationDao;
}
