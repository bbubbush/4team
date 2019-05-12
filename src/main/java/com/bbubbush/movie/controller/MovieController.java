package com.bbubbush.movie.controller;

import com.bbubbush.movie.dto.MovieSearchDTO;
import com.bbubbush.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping(value = "/getDailyMovie")
    public Map<String, Object> getDailyMovie(@RequestBody MovieSearchDTO param) {
        return movieService.getDailyMovie(param);
    }
}
