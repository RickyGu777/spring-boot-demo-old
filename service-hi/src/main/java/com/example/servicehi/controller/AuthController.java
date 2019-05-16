package com.example.servicehi.controller;

import com.example.servicehi.common.CodeMsg;
import com.example.servicehi.entity.Auth;
import com.example.servicehi.handler.GlobalException;
import com.example.servicehi.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/Auth")
@RestController
@AllArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService<Auth> authService;

    private GlobalException globalException = new GlobalException(CodeMsg.AUTH_CONTROLLER_BASE_ERROR);

    @PostMapping(value = "/selectAllAuth")
    public Object selectAllAuth(){
        try {
            return authService.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw this.globalException;
        }
    }

    @PostMapping(value = "/selectAuthTree")
    public Object selectAuthTree(Auth auth) {
        try {
            return authService.selectAuthTree(auth);
        } catch (Exception e) {
            e.printStackTrace();
            throw this.globalException;
        }
    }
}