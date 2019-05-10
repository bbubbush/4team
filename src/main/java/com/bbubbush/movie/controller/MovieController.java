package com.bbubbush.movie.controller;

import com.bbubbush.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieService movieService;

    @GetMapping(value = "/getDailyMovie")
    public Map<String, Object> getDailyMovie(@RequestBody Map<String, Object> param) {
//        param.forEach((key, value) -> {
//            System.out.println("Key :: " + key + ", Value :: " + param.get(key));
//        });
        return movieService.getDailyMovie(param);
    }
}
