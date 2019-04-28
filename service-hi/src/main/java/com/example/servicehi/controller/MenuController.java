package com.example.servicehi.controller;

import com.example.servicehi.entity.Menu;
import com.example.servicehi.service.MenuService;
import com.example.servicehi.util.ResponseUtil;
import com.example.servicehi.util.UUIDUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/Menu")
@RestController
@AllArgsConstructor
@Slf4j
public class MenuController {
    private final MenuService<Menu> menuService;

    @PostMapping(value = "/getMenuList")
    public ResponseUtil<List<Menu>> getMenuList(@RequestBody(required = false) Menu menu) {
        return new ResponseUtil(menuService.selectMenu(menu));
    }

    @PostMapping(value = "/createUUID")
    public ResponseUtil createUUID(){
//        ZipFileUtils.init("D://1");
//        ZipFileUtils.writeToZipFile("D://images");
//        ZipFileUtils.close();
//        AccessToken instance = AccessToken.getInstance();
        return new ResponseUtil(UUIDUtil.createUUID());
    }
}