package com.example.servicehi.controller;

import com.example.servicehi.common.CodeMsg;
import com.example.servicehi.entity.AhriUrl;
import com.example.servicehi.handler.GlobalException;
import com.example.servicehi.service.AhriUrlService;
import com.example.servicehi.util.JsoupTest;
import com.example.servicehi.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.datasource.DataSourceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            try {
                ahriUrlService.insert(ahriUrl);
            } catch (Exception e) {
                e.printStackTrace();
                throw new GlobalException(CodeMsg.AHRI_URL_CONRTROLLER_ERROR_INSERT_ERROR);
            }
            return new ResponseUtil("新增成功");
        } else {
            throw new GlobalException(CodeMsg.AHRI_URL_CONRTROLLER_ERROR_REPORT_URL);
        }
    }

    @PostMapping(value = "/download")
    public ResponseUtil download() {
        try {
            List<AhriUrl> ahriUrls = ahriUrlService.selectTodayURL();
            JsoupTest.addUrl(ahriUrls);
            JsoupTest instance = JsoupTest.getInstance();
            instance.Download();
            ahriUrls.forEach(t -> ahriUrlService.delete(t));
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(CodeMsg.AHRI_URL_CONRTROLLER_ERROR_DOWNLOAD_ERROR);
        }
        return new ResponseUtil();
    }

    @PostMapping(value = "/createZIP")
    public ResponseUtil createZIP() {
//        ZipFileUtils.init("D://1");
//        ZipFileUtils.writeToZipFile("D://images");
//        ZipFileUtils.close();
//        File[] files = ZipFileUtils.zipFileList("D:\\AirDroid");
//        for (File file : files) {
//            if (file.isDirectory()) {
//                ZipFileUtils.init("D://" + file.getName());
//                ZipFileUtils.writeToZipFile("D:\\AirDroid");
//                ZipFileUtils.close();
//            }
//        }
        return new ResponseUtil();
    }
}