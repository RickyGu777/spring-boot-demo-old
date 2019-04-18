package com.example.servicehi.service;

import com.example.servicehi.entity.TipsRelation;
import com.example.servicehi.entity.dto.DiaryDto;

import java.util.List;

public interface TipsRelationService <T extends TipsRelation> {
    void insert(T t);

    void insertList(List<T> t);

    void deleteByList(DiaryDto t);

    List<T> selectByNameAndOtherUUID(T t);
}
