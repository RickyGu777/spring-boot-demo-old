package com.example.servicehi.controller;

import com.example.servicehi.entity.Diary;
import com.example.servicehi.service.DiaryService;
import com.example.servicehi.util.ResponseUtil;
import com.github.pagehelper.PageHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/Diary")
@RestController
@AllArgsConstructor
@Slf4j
public class DiaryController {
    private final DiaryService diaryService;

    @PostMapping(value = "/addDiary")
    public ResponseUtil addDiary(@RequestBody Diary diary){
        diaryService.insert(diary);
        return new ResponseUtil();
    }

    @PostMapping(value = "/selectDiaryList")
    public ResponseUtil selectDiaryList(@RequestBody Diary diary){
        return new ResponseUtil<>(diaryService.selectDiaryList(diary));
    }

    @PostMapping(value = "/selectByUUID")
    public ResponseUtil selectByUUID(@RequestBody Diary diary){
        return new ResponseUtil<>(diaryService.selectByUUID(diary));
    }
}