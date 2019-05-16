package com.example.servicehi.controller;

import com.example.servicehi.entity.Auth;
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

    @PostMapping(value = "/selectAllAuth")
    public Object selectAllAuth() {
        return authService.selectAll();
    }

    @PostMapping(value = "/selectAuthTree")
    public Object selectAuthTree(Auth auth) {
        return authService.selectAuthTree(auth);
    }
}