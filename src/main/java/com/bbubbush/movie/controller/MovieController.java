package com.bbubbush.movie.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MovieController {
    public Map<String, Object> getDailyMovie(@RequestBody Map<String, Object> param) {
        param.forEach((key, value) -> {
            System.out.println("Key :: " + key + ", Value :: " + param.get(key));
        });
        return null;
    }
}
