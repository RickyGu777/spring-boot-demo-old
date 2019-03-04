package com.example.servicehi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
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
        fileMap.put("smfile", compress);
        Map<String, String> textMap = new HashMap<>();
        textMap.put("file_id", compress);

        String s = PostImg.formUpload("https://sm.ms/api/upload", textMap, fileMap);

        HashMap<Object, Object> hashMap = new HashMap<>();
        Map map = JSON.parseObject(s, new TypeReference<Map>() {
        });
        hashMap.put("img", ((Map)map.get("data")).get("url"));
        return hashMap;
    }
}