package com.example.servicehi.controller;

import com.example.servicehi.entity.HotWord;
import com.example.servicehi.service.HotWordService;
import com.example.servicehi.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public ResponseUtil ip(@RequestParam(value = "faculty", defaultValue = "公共") String faculty, @RequestParam(value = "course_name") String course_name) {
        log.info(faculty);
        log.info(course_name);
        return null;
//        String auth = BaiduTool.getAuth();
//        return new ResponseUtil(request.getHeader("X-Real-IP"));
    }
}
