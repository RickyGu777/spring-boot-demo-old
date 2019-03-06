package com.example.servicehi.service.impl;

import com.example.servicehi.dao.UploadImgDao;
import com.example.servicehi.entity.UploadImg;
import com.example.servicehi.service.UploadImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UploadImgServiceImpl<T extends UploadImg> implements UploadImgService<T> {
    @Autowired
    private UploadImgDao<T> uploadImgDao;

    @Override
    public void insert(T t) {
        t.setCreateDate(new Date());
        t.setModiDate(new Date());
        uploadImgDao.insert(t);
    }

    @Override
    public T selectImageInfoByOriginalName(T t) {
        return uploadImgDao.selectImageInfoByOriginalName(t);
    }
}
