package com.example.servicehi.service;

import com.example.servicehi.entity.TipsRelation;

import java.util.List;

public interface TipsRelationService <T extends TipsRelation> {
    void insert(T t);

    List<T> selectByNameAndOtherUUID(T t);
}
