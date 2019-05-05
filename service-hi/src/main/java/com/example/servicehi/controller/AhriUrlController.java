package com.example.servicehi.controller;

import com.example.servicehi.entity.AhriUrl;
import com.example.servicehi.service.AhriUrlService;
import com.example.servicehi.util.JsoupTest;
import com.example.servicehi.util.ResponseUtil;
import com.example.servicehi.util.ZipFileUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@RequestMapping(value = "/AhriUrl")
@RestController
@AllArgsConstructor
@Slf4j
public class AhriUrlController {
    private final AhriUrlService<AhriUrl> ahriUrlService;

    @PostMapping(value = "/selectAll")
    public Object selectAll() {
        return ahriUrlService.selectAll();
    }

    @PostMapping(value = "/insert")
    public ResponseUtil insert(@RequestBody AhriUrl ahriUrl) {
        List<AhriUrl> ahriUrls = ahriUrlService.selectURL(ahriUrl);
        if (ahriUrls.isEmpty()) {
            ahriUrlService.insert(ahriUrl);
            log.info("新增成功");
            return new ResponseUtil();
        } else {
            log.error("有重复URL");
            return ResponseUtil.buildERROR("有重复URL");
        }
    }

    @PostMapping(value = "/download")
    public ResponseUtil download() {
        List<AhriUrl> ahriUrls = ahriUrlService.selectTodayURL();
        JsoupTest.addUrl(ahriUrls);
        JsoupTest instance = JsoupTest.getInstance();
        instance.Download();
        ahriUrls.forEach(t -> ahriUrlService.delete(t));
        return new ResponseUtil();
    }

    @PostMapping(value = "/createZIP")
    public ResponseUtil createZIP() {
//        ZipFileUtils.init("D://1");
//        ZipFileUtils.writeToZipFile("D://images");
//        ZipFileUtils.close();
        File[] files = ZipFileUtils.zipFileList("D:\\AirDroid");
        for (File file : files) {
            if (file.isDirectory()) {
                ZipFileUtils.init("D://" + file.getName());
                ZipFileUtils.writeToZipFile("D:\\AirDroid");
                ZipFileUtils.close();
            }
        }
        return new ResponseUtil();
    }
}