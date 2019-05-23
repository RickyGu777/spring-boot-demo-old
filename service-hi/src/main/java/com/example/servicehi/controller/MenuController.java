package com.example.servicehi.controller;

import com.example.servicehi.entity.Menu;
import com.example.servicehi.service.MenuService;
import com.example.servicehi.util.ResponseUtil;
import com.example.servicehi.util.UUIDUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

@RequestMapping(value = "/Menu")
@RestController
@AllArgsConstructor
@Slf4j
public class MenuController {
    private final MenuService<Menu> menuService;

    private final RedisTemplate redisTemplate;

    @PostMapping(value = "/getMenuList")
    public ResponseUtil<List<Menu>> getMenuList(@RequestBody(required = false) Menu menu) {
        List<Menu> menus = menuService.selectMenu(menu);
        return new ResponseUtil(menus);
    }

    @PostMapping(value = "/createUUID")
    public ResponseUtil createUUID(){
        Calendar instance = Calendar.getInstance();
        Set keys = redisTemplate.keys("IP-*-" + instance.get(Calendar.YEAR) + instance.get(Calendar.MONTH) + instance.get(Calendar.DATE));
        return new ResponseUtil(UUIDUtil.createUUID());
    }
}