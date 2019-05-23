package com.example.servicehi.controller;

import com.example.servicehi.entity.HotWord;
import com.example.servicehi.service.HotWordService;
import com.example.servicehi.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "/Test")
@RestController
@AllArgsConstructor
@Slf4j
public class TestController {
    private final HotWordService<HotWord> hotWordService;

    @PostMapping(value = "/add")
    public ResponseUtil add(@RequestBody HotWord hotWord) {
        hotWordService.insert(hotWord);
        return new ResponseUtil();
    }

    @PostMapping(value = "/ip")
    public ResponseUtil ip(HttpServletRequest request) {
        return new ResponseUtil(request.getRemoteAddr());
    }
}
