package com.example.servicehi.controller;

import com.example.servicehi.entity.Diary;
import com.example.servicehi.service.DiaryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/Diary")
@RestController
@AllArgsConstructor
@Slf4j
public class DiaryController {
    private final DiaryService diaryService;

    @PostMapping(value = "/addDiary")
    public void addDiary(Diary diary){
        diaryService.insert(diary);
    }
}