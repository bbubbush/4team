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

    @GetMapping(value = "/getDailyMovie")
    public Map<String, Object> getDailyMovie(@ModelAttribute MovieSearchDTO param) {
        System.out.println(param.getTargetDt());
        Map<String, Object> result = movieService.getDailyMovie(param);
        result.put("data",  result.get("dailyBoxOfficeList"));
        return result;
    }
}
