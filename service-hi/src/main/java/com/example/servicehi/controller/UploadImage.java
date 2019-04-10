package com.example.servicehi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.servicehi.common.Config;
import com.example.servicehi.entity.ShareTicketImg;
import com.example.servicehi.entity.UploadImg;
import com.example.servicehi.service.ShareTicketImgService;
import com.example.servicehi.service.UploadImgService;
import com.example.servicehi.util.ResponseUtil;
import com.example.servicehi.util.SaveAndPostImg;
import com.example.servicehi.util.UUIDUtil;
import com.example.servicehi.util.ZixingCodeUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.SystemUtils;
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

    private final ShareTicketImgService<ShareTicketImg> shareTicketImgService;

    private final Config config;

    /**
     * 上传图片
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/upload")
    public Map uploadImage(@RequestParam("img") MultipartFile multipartFile) throws IOException {
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
        uploadImg.setTitle(uploadImg.getRandomName());
        uploadImgService.insert(uploadImg);
        return hashMap;
    }

    /**
     * 修改上传文件的标题
     *
     * @param uploadImg
     * @return
     */
    @PostMapping(value = "/updateTitle")
    public ResponseUtil updateTitle(@RequestBody UploadImg uploadImg) {
        uploadImgService.updateTitle(uploadImg);
        return new ResponseUtil();
    }

    /**
     * 识别微信二维码
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/QRCode")
    public ResponseUtil QRCode(@RequestParam("file") MultipartFile multipartFile) throws IOException {
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
        uploadImg.setTitle(uploadImg.getRandomName());
        uploadImgService.insert(uploadImg);
        String filePath = SystemUtils.IS_OS_LINUX ? config.getLinux() : config.getWindows() + "/" + fileName;
        String qrCode = ZixingCodeUtil.decodeQRCodeImage(filePath, null).replace("\uD83D\uDCF1", "");
        ShareTicketImg shareTicketImg = new ShareTicketImg();
        shareTicketImg.setUploadImgUUID(uploadImg.getUuid());
        shareTicketImg.setQRCode(qrCode);
        shareTicketImgService.insert(shareTicketImg);
        return new ResponseUtil(qrCode);
    }
}