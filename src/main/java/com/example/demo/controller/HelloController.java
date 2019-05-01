package com.example.demo.controller;

import com.example.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class HelloController {
    @Autowired
    HelloService helloService;

    @GetMapping("/")
    public String hello() {
        return "Hello, world!";
    }

    @GetMapping("/getList")
    public List<Map<String, Object>> getList() {
        List<Map<String, Object>> list = helloService.getList();
        return list;
    }

}
