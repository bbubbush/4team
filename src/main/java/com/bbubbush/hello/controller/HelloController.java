package com.bbubbush.hello.controller;

import com.bbubbush.hello.dto.MemberDTO;
import com.bbubbush.hello.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    @PostMapping("/insertMember")
    public String insertMember(@RequestBody MemberDTO member) {
        int result = helloService.insertMember(member);
        return result > 0 ? "success" : "fail";
    }

}
