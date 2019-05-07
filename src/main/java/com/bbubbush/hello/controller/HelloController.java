package com.bbubbush.hello.controller;

import com.bbubbush.hello.dto.MemberDTO;
import com.bbubbush.hello.service.HelloService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {
    @Autowired
    HelloService helloService;

    @ApiOperation(value = "헬로, 월드")
    @GetMapping("/")
    public String hello() {
        // RestTemplate 에 MessageConverter 세팅
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new FormHttpMessageConverter());
        converters.add(new StringHttpMessageConverter());

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(converters);

        // parameter 세팅
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("key", "851364883b76175e8c780b5744e817e8");
        map.add("targetDt", "20190507");

        // REST API 호출
        String result = restTemplate.postForObject("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json", map, String.class);
        System.out.println("------------------ TEST 결과 ------------------");
        System.out.println(result);



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
