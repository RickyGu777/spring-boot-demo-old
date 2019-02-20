package com.example.servicemiya.controller;

import brave.sampler.Sampler;
import com.example.servicemiya.Service.SchedualServiceHi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;

@RestController
@RequestMapping
@Slf4j
public class ZipkinTestController {

    //编译器报错，无视。 因为这个Bean是在程序启动的时候注入的，编译器感知不到，所以报错。
    @Autowired
    SchedualServiceHi schedualServiceHi;

    @RequestMapping(value = "/fghi")
    public String sayHi(@RequestParam String name) {
        return schedualServiceHi.sayHiFromClientOne( name );
    }

//    @RequestMapping("/hi")
//    public String home() {
//        log.info(Level.INFO + "hi is being called");
//        return "hi i'm miya!";
//    }
//
//    @RequestMapping("/miya")
//    public String info() {
//        log.info(Level.INFO + "info is being called");
//        return restTemplate.getForObject("http://localhost:8762/info", String.class);
//    }
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Bean
//    public RestTemplate getRestTemplate() {
//        return new RestTemplate();
//    }
//
//
//    @Bean
//    public Sampler defaultSampler() {
//        return Sampler.ALWAYS_SAMPLE;
//    }
}