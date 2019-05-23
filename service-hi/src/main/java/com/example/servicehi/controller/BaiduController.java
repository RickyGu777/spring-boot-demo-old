package com.example.servicehi.controller;

import com.example.servicehi.entity.BaiduOCR;
import com.example.servicehi.entity.BaiduOCRWords;
import com.example.servicehi.service.BaiduOCRService;
import com.example.servicehi.service.BaiduOCRWordsService;
import com.example.servicehi.util.Baidu.BaiduTool;
import com.example.servicehi.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping(value = "/Baidu")
@RestController
@AllArgsConstructor
@Slf4j
public class BaiduController {
    private final BaiduOCRService<BaiduOCR> baiduOCRService;

    private final BaiduOCRWordsService<BaiduOCRWords> baiduOCRWordsService;

    private final RedisTemplate redisTemplate;

    @PostMapping(value = "/getAccessToke")
    public ResponseUtil<String> getAccessToke() {
        return new ResponseUtil<>(redisTemplate.opsForValue().get("baiduAccessToken").toString());
    }

    @PostMapping(value = "/OCR")
    public ResponseUtil ocr(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return new ResponseUtil(baiduOCRWordsService.wordOCR(multipartFile));
    }

    @PostMapping(value = "/getOCRList")
    public ResponseUtil getOCRList(@RequestBody BaiduOCR baiduOCR) {
        return new ResponseUtil(baiduOCRService.selectOCRList(baiduOCR));
    }

    @PostMapping(value = "/delete")
    public ResponseUtil delete(@RequestBody BaiduOCR baiduOCR) {
        baiduOCRService.delete(baiduOCR);
        return new ResponseUtil();
    }
}
