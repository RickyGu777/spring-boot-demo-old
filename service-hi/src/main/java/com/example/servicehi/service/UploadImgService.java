package com.example.servicehi.service;

import com.example.servicehi.entity.UploadImg;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UploadImgService<T extends UploadImg> {
    void insert(T t);

    List<T> selectImageInfoByOriginalName(T t);

    T selectImageInfoByRandomName(T t);

    void updateTitle(T t);

    PageInfo selectPictureWall(T t);
}