package com.example.servicehi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.servicehi.common.Config;
import com.example.servicehi.entity.BaiduOCR;
import com.example.servicehi.entity.BaiduOCRWords;
import com.example.servicehi.entity.UploadImg;
import com.example.servicehi.entity.dto.BaiduOCRDto;
import com.example.servicehi.service.BaiduOCRService;
import com.example.servicehi.service.BaiduOCRWordsService;
import com.example.servicehi.service.UploadImgService;
import com.example.servicehi.util.Baidu.BaiduTool;
import com.example.servicehi.util.HttpRequest;
import com.example.servicehi.util.ResponseUtil;
import com.example.servicehi.util.SaveAndPostImg;
import com.example.servicehi.util.UUIDUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.SystemUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@RequestMapping(value = "/Baidu")
@RestController
@AllArgsConstructor
@Slf4j
public class BaiduController {
    private final BaiduOCRService<BaiduOCR> baiduOCRService;

    private final BaiduOCRWordsService<BaiduOCRWords> baiduOCRWordsService;

    private final UploadImgService<UploadImg> uploadImgService;

    private final Config config;

    @PostMapping(value = "/getAccessToke")
    public ResponseUtil<String> getAccessToke() {
        return new ResponseUtil<>(BaiduTool.getAuth());
    }

    @Transactional
    @PostMapping(value = "/OCR")
    public ResponseUtil ocr(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        BASE64Encoder encoder = new BASE64Encoder();
        String imgData = encoder.encode(multipartFile.getBytes()).replace("\r\n", "");
        // 上传至图床
        String originalFilename = multipartFile.getOriginalFilename();
        String fileName = UUIDUtil.createUUID() + "." + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
        String compress = SaveAndPostImg.compress(multipartFile, SystemUtils.IS_OS_LINUX ? config.getLinux() : config.getWindows(), fileName);
        Map map = JSON.parseObject(compress, new TypeReference<Map>() {
        });
        HashMap<String, Object> hashMap = new HashMap<>();
        UploadImg uploadImg = new UploadImg();
        uploadImg.setOriginalName(originalFilename);
        uploadImg.setIsDel("0");
        uploadImg.setRandomName(fileName);
        if ("success".equals(map.get("code").toString())) {
            String responseUrl = ((Map) map.get("data")).get("url").toString();
            uploadImg.setResponseUrl(responseUrl);
            hashMap.put("img", ((Map) map.get("data")).get("url"));
            hashMap.put("code", 0);
        } else if ("error".equals(map.get("code").toString())) {
            hashMap.put("msg", map.get("msg"));
            hashMap.put("code", 1);
        }
        uploadImg.setStatus("1");
        uploadImgService.insert(uploadImg);

        // 上传至百度
        imgData = URLEncoder.encode(imgData, "UTF-8");
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";
        String param = "?language_type=CHN_ENG&access_token=" + BaiduTool.getAuth() + "&image=" + imgData;
        String s = HttpRequest.baiduOCRPost(url, param);
        BaiduOCRDto baiduOCRDto = JSON.parseObject(s, new TypeReference<BaiduOCRDto>() {
        });
        baiduOCRDto.setUploadImgUUID(uploadImg.getUuid());
        baiduOCRDto.getWordsResult().stream().filter(words -> words.getBaiduOCRUUID() == null).forEach(student -> {
            student.setBaiduOCRUUID(baiduOCRDto.getUuid());
            student.setIsDel("0");
        });
        baiduOCRService.insert(baiduOCRDto);
        baiduOCRWordsService.insertList(baiduOCRDto.getWordsResult());
        return new ResponseUtil(baiduOCRDto);
    }

    @PostMapping(value = "/getOCRList")
    public ResponseUtil getOCRList(@RequestBody BaiduOCR baiduOCR) {
        return new ResponseUtil(baiduOCRService.selectOCRList(baiduOCR));
    }
}
