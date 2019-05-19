package com.example.servicehi.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.servicehi.common.Config;
import com.example.servicehi.dao.BaiduOCRWordsDao;
import com.example.servicehi.entity.BaiduOCR;
import com.example.servicehi.entity.BaiduOCRWords;
import com.example.servicehi.entity.UploadImg;
import com.example.servicehi.entity.dto.BaiduOCRDto;
import com.example.servicehi.service.BaiduOCRService;
import com.example.servicehi.service.BaiduOCRWordsService;
import com.example.servicehi.service.UploadImgService;
import com.example.servicehi.util.Baidu.BaiduTool;
import com.example.servicehi.util.HttpRequest;
import com.example.servicehi.util.SaveAndPostImg;
import com.example.servicehi.util.UUIDUtil;
import org.apache.commons.lang.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BaiduOCRWordsServiceImpl<T extends BaiduOCRWords> implements BaiduOCRWordsService<T> {
    @Autowired
    private BaiduOCRWordsDao<T> baiduOCRWordsDao;

    @Autowired
    private BaiduOCRService<BaiduOCR> baiduOCRService;

    @Autowired
    private Config config;

    @Autowired
    private UploadImgService<UploadImg> uploadImgService;

    @Override
    public void insert(T t) {
        baiduOCRWordsDao.insert(t);
    }

    @Override
    public void insertList(List<T> t) {
        baiduOCRWordsDao.insertList(t);
    }

    @Override
    public T selectByUUID(T t) {
        return baiduOCRWordsDao.selectByUUID(t);
    }

    @Override
    public List<T> selectByBaiduOCRUUID(T t) {
        return baiduOCRWordsDao.selectByBaiduOCRUUID(t);
    }

    @Override
    public BaiduOCRDto wordOCR(MultipartFile multipartFile) throws IOException {
        BASE64Encoder encoder = new BASE64Encoder();
        String imgData = encoder.encode(multipartFile.getBytes()).replace("\r\n", "");
        // 上传至图床
        String originalFilename = multipartFile.getOriginalFilename();
        String fileName = UUIDUtil.createUUID() + "." + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
        String compress = SaveAndPostImg.compress(multipartFile, config.getFilePath(), fileName);
        Map map = JSON.parseObject(compress, Map.class);
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
        uploadImg.setTitle(uploadImg.getRandomName());
        if (!SystemUtils.IS_OS_LINUX) {
            uploadImg.setImagePath('.' + config.getLinuxPath() + uploadImg.getRandomName());
        }
        uploadImgService.insert(uploadImg);

        // 上传至百度
        imgData = URLEncoder.encode(imgData, "UTF-8");
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";
        String param = "?language_type=CHN_ENG&access_token=" + BaiduTool.getAuth() + "&image=" + imgData;
        BaiduOCRDto baiduOCRDto = JSON.parseObject(HttpRequest.baiduOCRPost(url, param), BaiduOCRDto.class);
        baiduOCRDto.setUploadImgUUID(uploadImg.getUuid());
        baiduOCRService.insert(baiduOCRDto);
        if (baiduOCRDto.getErrorCode() == null) {
            baiduOCRDto.getWordsResult().stream().filter(words -> words.getBaiduOCRUUID() == null).forEach(student -> {
                student.setBaiduOCRUUID(baiduOCRDto.getUuid());
                student.setIsDel("0");
            });
            insertList((List<T>) baiduOCRDto.getWordsResult());
        }
        if (SystemUtils.IS_OS_WINDOWS) {
            SaveAndPostImg.sendImage(config.getFilePath() + uploadImg.getRandomName());
        }
        return baiduOCRDto;
    }
}
