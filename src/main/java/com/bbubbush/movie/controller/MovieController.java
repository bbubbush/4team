package com.bbubbush.movie.controller;

import com.bbubbush.movie.dto.MovieSearchDTO;
import com.bbubbush.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieService movieService;

    /**
     * 박스오피스 조회 api 연습문제
     * */
    @GetMapping(value = "/getDailyMovie")
    public Map<String, Object> getDailyMovie(@ModelAttribute MovieSearchDTO param) {
        Map<String, Object> result = movieService.getDailyMovie(param);
        return result;
    }
}
