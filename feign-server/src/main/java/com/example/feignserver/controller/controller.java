package com.example.feignserver.controller;

import com.example.feignserver.feignserver.SchedualServiceHi;
import com.example.feignserver.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/test")
@RestController
@Slf4j
public class controller {
    @Autowired
    private SchedualServiceHi schedualServiceHi;

    @RequestMapping(value = "/hi")
    public ResponseUtil hi(){
        return new ResponseUtil();
    }
}
