package com.example.servicehi.service.impl;

import com.example.servicehi.dao.UserMenuRelationDao;
import com.example.servicehi.entity.UserMenuRelation;
import com.example.servicehi.service.UserMenuRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMenuRelationServiceImpl<T extends UserMenuRelation> implements UserMenuRelationService<T> {
    @Autowired
    private UserMenuRelationDao<T> userMenuRelationDao;
}
