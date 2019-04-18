package com.example.servicehi.dao;

import com.example.servicehi.entity.TipsRelation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipsRelationDao<T extends TipsRelation> {
    void insert(T t);

    void insertList(List<T> t);

    List<T> selectByNameAndOtherUUID(T t);
}