package com.example.servicehi.controller;

import com.example.servicehi.entity.Joke;
import com.example.servicehi.service.JokeService;
import com.example.servicehi.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/Joke")
@RestController
@AllArgsConstructor
@Slf4j
public class JokeController {
    private final JokeService<Joke> jokeJokeService;

    @PostMapping(value = "/selectByTitle")
    public ResponseUtil selectByTitle(@RequestBody Joke joke) {
        return new ResponseUtil(jokeJokeService.selectByTitle(joke));
    }

    @PostMapping(value = "/insert")
    public ResponseUtil insert(@RequestBody Joke joke) {
        jokeJokeService.insert(joke);
        return new ResponseUtil();
    }

    @PostMapping(value = "/selectJokeList")
    public ResponseUtil selectJokeList(Joke joke) {
        return new ResponseUtil(jokeJokeService.selectJokeList(joke));
    }
}