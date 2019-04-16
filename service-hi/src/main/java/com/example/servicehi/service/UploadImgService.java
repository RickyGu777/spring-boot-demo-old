package com.example.servicehi.service;

import com.example.servicehi.entity.UploadImg;
import com.github.pagehelper.PageInfo;

public interface UploadImgService<T extends UploadImg> {
    void insert(T t);

    T selectImageInfoByOriginalName(T t);

    void updateTitle(T t);

    PageInfo selectPictureWall(T t);
}