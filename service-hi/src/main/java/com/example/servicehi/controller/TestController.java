package com.example.servicehi.controller;

import com.example.servicehi.common.Config;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@AllArgsConstructor
public class TestController {
//    @Value("${server.port}")
//    String port;

    private final Config config;

    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        return "hi " + name + " ,i am from port:" ;
//                port;
    }

    @RequestMapping("/config")
    public String config(){
        return config.getLinux();
    }
}
