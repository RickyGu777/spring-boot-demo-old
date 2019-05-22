package com.example.servicehi.controller;

import com.alibaba.fastjson.JSON;
import com.example.servicehi.entity.DbConfig;
import com.example.servicehi.service.DbConfigService;
import com.example.servicehi.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/DbConfig")
@RestController
@AllArgsConstructor
@Slf4j
public class DbConfigController {
    private final DbConfigService<DbConfig> dbConfigService;

    private final RedisTemplate redisTemplate;

    @PostMapping(value = "/getRealTimeConfigByCode")
    public ResponseUtil getRealTimeConfigByCode(@RequestBody DbConfig dbConfig) {
        return new ResponseUtil(dbConfigService.selectByCode(dbConfig));
    }

    @PostMapping(value = "/getConfigByCode")
    public ResponseUtil getConfigByCode(@RequestBody DbConfig dbConfig) {
        Validate.isTrue(StringUtils.isNotEmpty(dbConfig.getCode()), "查询配置CODE不能为空，请检查");
        return new ResponseUtil(JSON.parseObject(redisTemplate.opsForValue().get(dbConfig.getCode()).toString(), DbConfig.class));
    }
}