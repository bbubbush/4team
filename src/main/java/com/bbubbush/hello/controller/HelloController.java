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
        // 조회일자
        String targetDt = "20190506";

        // 발급키
        String key = "851364883b76175e8c780b5744e817e8";

        // Rest Client 통해 호출
        KobisOpenAPIRestService service = new KobisOpenAPIRestService(key);

        // 일일 박스오피스 서비스 호출
        Map<String, String> params = new HashMap<String, String>();
        params.put("targetDt", targetDt);
        try {
            String dailyResponse = service.getDailyBoxOffice(true, params);

            // JSON 사용
            ObjectMapper mapper = new ObjectMapper();
            HashMap<String, Object> dailyResult = mapper.readValue(dailyResponse, HashMap.class);
            LinkedHashMap<String, Object> boxOfficeResult = (LinkedHashMap<String, Object>)dailyResult.get("boxOfficeResult");
            boxOfficeResult.forEach((k, value) -> {
                System.out.println("key :: " + k + ", value :: " + boxOfficeResult.get(k));
            });
            ArrayList<LinkedHashMap<String, Object>> dailyBoxOfficeList = (ArrayList<LinkedHashMap<String, Object>>)boxOfficeResult.get("dailyBoxOfficeList");
            for (LinkedHashMap<String, Object> map: dailyBoxOfficeList) {
                map.forEach((k, v) -> {
                    System.out.println("key :: " + k + ", value :: " + map.get(k));
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }




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
