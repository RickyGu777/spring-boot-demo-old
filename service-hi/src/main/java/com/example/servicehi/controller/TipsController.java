package com.example.servicehi.controller;

import com.example.servicehi.entity.Tips;
import com.example.servicehi.service.TipsService;
import com.example.servicehi.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/Tips")
@RestController
@AllArgsConstructor
@Slf4j
public class TipsController {
    private final TipsService<Tips> tipsService;

    @PostMapping(value = "/selectDiaryTips")
    public ResponseUtil selectDiaryTips() {
        Tips tips = new Tips();
        tips.setStatus("1");
        return new ResponseUtil(tipsService.selectTipsType(tips));
    }

    @PostMapping(value = "/insert")
    public ResponseUtil insert(@RequestBody Tips tips) {
        tipsService.insert(tips);
        return new ResponseUtil();
    }
}