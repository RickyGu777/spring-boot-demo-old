package com.example.servicemiya.controller;

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
    @RequestMapping("/hi")
    public String home() {
        log.info(Level.INFO + "hi is being called");
        return "hi i'm miya!";
    }

    @RequestMapping("/miya")
    public String info() {
        log.info(Level.INFO + "info is being called");
        return restTemplate.getForObject("http://localhost:8762/info", String.class);
    }

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}