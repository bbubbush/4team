package com.bbubbush.hello.controller;

import com.bbubbush.hello.dto.MemberDTO;
import com.bbubbush.hello.service.HelloService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;

import java.util.*;

@RestController
public class HelloController {
    @Autowired
    HelloService helloService;

    @ApiOperation(value = "헬로, 월드")
    @GetMapping("/")
    public String hello() {
        return "Hello, world!";
    }

    @ApiOperation(value = "전체목록 조회")
    @GetMapping("/getMeberList")
    public List<Map<String, Object>> getMeberList() {
        return helloService.getMeberList();
    }

    @ApiOperation(value = "회원 추가")
    @PostMapping("/insertMember")
    public String insertMember(@RequestBody MemberDTO member) {
        return helloService.insertMember(member) > 0 ? "success" : "fail";
    }

    @ApiOperation(value = "회원이름 변경")
    @ApiImplicitParam(name = "member", value = "회원정보(이름, 번호 필수)")
    @PutMapping("/updateMember")
    public String updateMember(@RequestBody MemberDTO member) {
        return helloService.updateMember(member) > 0 ? "success" : "fail";
    }

    @ApiOperation(value = "회원 삭제")
    @ApiImplicitParam(name = "member", value = "회원정보(번호 필수)")
    @DeleteMapping("/deleteMember")
    public String deleteMember(@RequestBody MemberDTO member) {
        return helloService.deleteMember(member) > 0 ? "success" : "fail";
    }

}
