package com.example.servicehi.controller;

import brave.sampler.Sampler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;

@RestController
@RequestMapping
@Slf4j
public class ZipkinTestController {
    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/zipkinhi")
    public String callHome() {
        log.info(Level.INFO + "calling trace service-hi  ");
        return restTemplate.getForObject("http://localhost:8989/miya", String.class);
    }

    @RequestMapping("/info")
    public String info() {
        log.info(Level.INFO + "calling trace service-hi ");
        return "i'm service-hi";

    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

}