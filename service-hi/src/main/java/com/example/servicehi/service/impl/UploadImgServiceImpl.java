package com.example.servicehi.service.impl;

import com.example.servicehi.dao.UploadImgDao;
import com.example.servicehi.entity.UploadImg;
import com.example.servicehi.service.UploadImgService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    public List<T> selectImageInfoByOriginalName(T t) {
        return uploadImgDao.selectImageInfoByOriginalName(t);
    }

    @Override
    public T selectImageInfoByRandomName(T t) {
        return uploadImgDao.selectImageInfoByRandomName(t);
    }

    @Override
    public void updateTitle(T t) {
        t.setModiDate(new Date());
        uploadImgDao.updateTitle(t);
    }

    @Override
    public PageInfo selectPictureWall(T t) {
        PageHelper.startPage(t.getPage(), t.getSize());
        return new PageInfo(uploadImgDao.selectPictureWall(t));
    }
}
