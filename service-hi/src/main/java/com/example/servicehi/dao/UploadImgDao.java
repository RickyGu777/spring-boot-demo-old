package com.example.servicehi.dao;

import com.example.servicehi.entity.Diary;
import com.example.servicehi.entity.UploadImg;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadImgDao<T extends UploadImg> {
    void insert(T t);

    T selectImageInfoByOriginalName(T t);

    T selectByUUID(T t);

    void updateTitle(T t);
}