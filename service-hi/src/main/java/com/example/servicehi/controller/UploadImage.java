package com.example.servicehi.controller;

import com.util.SaveAndPostImg;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = "/UploadImage")
public class UploadImage {

    @PostMapping(value = "/upload")
    public Map uploadImage(@RequestParam("img") MultipartFile multipartFile) throws IOException {
        return SaveAndPostImg.compress(multipartFile, "D:/images/", "https://sm.ms/api/upload");
    }
}