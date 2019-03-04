package com.example.servicehi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.servicehi.entity.UploadImg;
import com.example.servicehi.service.UploadImgService;
import com.util.SaveAndPostImg;
import com.util.UUIDUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping(value = "/UploadImage")
@RestController
@AllArgsConstructor
@Slf4j
public class UploadImage {
    private final UploadImgService<UploadImg> uploadImgService;

    @PostMapping(value = "/upload")
    public Map uploadImage(@RequestParam("img") MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String fileName = UUIDUtil.createUUID() + "." + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
        String compress = SaveAndPostImg.compress(multipartFile, "D:/images", fileName, "https://sm.ms/api/upload");
        Map map = JSON.parseObject(compress, new TypeReference<Map>() {
        });
        HashMap<String, Object> hashMap = new HashMap<>();
        if ("success".equals(map.get("code").toString())) {
            String responseUrl = ((Map) map.get("data")).get("url").toString();
            UploadImg uploadImg = new UploadImg();
            uploadImg.setOriginalName(originalFilename);
            uploadImg.setIsDel("0");
            uploadImg.setRandomName(fileName);
            uploadImg.setResponseUrl(responseUrl);
            uploadImgService.insert(uploadImg);
            hashMap.put("img", ((Map) map.get("data")).get("url"));
            hashMap.put("code", 0);
        } else if ("error".equals(map.get("code").toString())) {
            hashMap.put("msg", map.get("msg"));
            hashMap.put("code", 1);
        }
        return hashMap;
    }
}