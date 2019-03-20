package com.example.servicehi.controller;

import com.example.servicehi.util.Baidu.BaiduTool;
import com.example.servicehi.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/Baidu")
@RestController
@AllArgsConstructor
@Slf4j
public class BaiduController {
    @PostMapping(value = "/getAccessToke")
    public ResponseUtil<String> getAccessToke(){
        return new ResponseUtil<>(BaiduTool.getAuth());
    }
}
