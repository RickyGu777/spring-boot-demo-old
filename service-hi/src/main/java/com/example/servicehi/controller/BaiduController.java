package com.example.servicehi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.servicehi.entity.BaiduOCR;
import com.example.servicehi.entity.dto.BaiduOCRDto;
import com.example.servicehi.service.BaiduOCRService;
import com.example.servicehi.util.Baidu.BaiduTool;
import com.example.servicehi.util.HttpRequest;
import com.example.servicehi.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

@RequestMapping(value = "/Baidu")
@RestController
@AllArgsConstructor
@Slf4j
public class BaiduController {
    private final BaiduOCRService<BaiduOCR> baiduOCRService;

    @PostMapping(value = "/getAccessToke")
    public ResponseUtil<String> getAccessToke() {
        return new ResponseUtil<>(BaiduTool.getAuth());
    }

    @PostMapping(value = "/OCR")
    public ResponseUtil ocr(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        BASE64Encoder encoder = new BASE64Encoder();
        String imgData = encoder.encode(multipartFile.getBytes()).replace("\r\n","");
        imgData = URLEncoder.encode(imgData,"UTF-8");
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";
        String param = "?language_type=CHN_ENG&access_token=" + BaiduTool.getAuth() + "&image=" + imgData;
        String s = HttpRequest.baiduOCRPost(url, param);
        BaiduOCRDto baiduOCRDto = JSON.parseObject(s, new TypeReference<BaiduOCRDto>() {
        });
        return new ResponseUtil(s);
    }
}
