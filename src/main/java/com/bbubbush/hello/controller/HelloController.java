package com.bbubbush.hello.controller;

import com.bbubbush.hello.dto.MemberDTO;
import com.bbubbush.hello.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getMeberList")
    public List<Map<String, Object>> getMeberList() {
        List<Map<String, Object>> list = helloService.getMeberList();
        return list;
    }
    @PostMapping("/insertMember")
    public String insertMember(@RequestBody MemberDTO member) {
        int result = helloService.insertMember(member);
        return result > 0 ? "success" : "fail";
    }
    @PutMapping("/updateMember")
    public String updateMember(@RequestBody MemberDTO member) {
        int result = helloService.updateMember(member);
        return result > 0 ? "success" : "fail";
    }
    @DeleteMapping("/deleteMember")
    public String deleteMember(@RequestBody MemberDTO member) {
        int result = helloService.deleteMember(member);
        return result > 0 ? "success" : "fail";
    }

}
