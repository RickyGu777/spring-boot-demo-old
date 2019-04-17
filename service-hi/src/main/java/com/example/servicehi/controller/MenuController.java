package com.example.servicehi.controller;

import com.example.servicehi.entity.Menu;
import com.example.servicehi.service.MenuService;
import com.example.servicehi.util.ResponseUtil;
import com.example.servicehi.util.UUIDUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "/Menu")
@RestController
@AllArgsConstructor
@Slf4j
public class MenuController {
    private final MenuService<Menu> menuService;

    @PostMapping(value = "/getBackMenuList")
    private ResponseUtil<List<Menu>> getBackMenuList(@RequestBody(required = false) Menu menu) {
        return new ResponseUtil(menuService.selectMenu(menu));
    }

    @PostMapping(value = "/createUUID")
    private ResponseUtil createUUID(){
        return new ResponseUtil(UUIDUtil.createUUID());
    }
}