package com.example.servicehi.service;

import com.example.servicehi.entity.UploadImg;

public interface UploadImgService<T extends UploadImg> {
    void insert(T t);
}