package com.example.servicehi.controller;

import com.example.servicehi.util.CompressImageUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/UploadImage")
public class UploadImage {

    @PostMapping(value = "/upload")
    public String uploadImage(@RequestParam("file") MultipartFile multipartFile) {
        CompressImageUtil.compress(multipartFile);
        return "创建图片完成";
    }
}