package com.example.wechat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/test")
@Slf4j
public class TestController {

    @PostMapping(value = "/demo")
    public Object demo(@RequestBody(required = false) Object ...objects){
        return objects;
    }
}