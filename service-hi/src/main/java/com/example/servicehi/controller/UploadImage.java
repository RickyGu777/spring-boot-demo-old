package com.example.servicehi.controller;

import com.util.CompressImageUtil;
import com.util.PostImg;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/UploadImage")
public class UploadImage {

    @PostMapping(value = "/upload")
    public Map uploadImage(@RequestParam("img") MultipartFile multipartFile) {
        String compress = CompressImageUtil.compress(multipartFile);

        Map<String, String> fileMap = new HashMap<>();
        fileMap.put("file", compress);
        Map<String, String> textMap = new HashMap<String, String>();
        textMap.put("smfile", multipartFile.getOriginalFilename());

        PostImg.formUpload(textMap, fileMap);

        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("file", compress);
        return hashMap;
    }
}