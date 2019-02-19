package com.example.servicehi.controller;

import com.example.servicehi.entity.AhriUrl;
import com.example.servicehi.service.AhriUrlService;
import com.example.servicehi.util.JsoupTest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "/AhriUrl")
@RestController
@AllArgsConstructor
@Slf4j
public class AhriUrlController {
    private final AhriUrlService<AhriUrl> ahriUrlService;

    @RequestMapping(value = "/selectAll")
    public Object selectAll() {
        return ahriUrlService.selectAll();
    }

    @PostMapping(value = "/insert")
    public String insert(@RequestBody Map url) {
        AhriUrl ahriUrl = new AhriUrl();
        ahriUrl.setUrl(url.get("url").toString());
        List<AhriUrl> ahriUrls = ahriUrlService.selectURL(ahriUrl);

        if (ahriUrls.isEmpty()) {
            ahriUrlService.insert(ahriUrl);
            log.info("新增成功");
            return "新增成功";
        } else {
            log.error("有重复URL");
            return "有重复URL";
        }
    }

    @PostMapping(value = "/test")
    public String test() {
        List<AhriUrl> ahriUrls = ahriUrlService.selectTodayURL();
        JsoupTest.addUrl(ahriUrls);
        JsoupTest instance = JsoupTest.getInstance();
        instance.Download();
        ahriUrls.forEach(t -> ahriUrlService.delete(t));
        return "over";
    }
}