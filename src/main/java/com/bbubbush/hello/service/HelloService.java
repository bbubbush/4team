package com.bbubbush.hello.service;

import com.bbubbush.hello.dto.MemberDTO;
import com.bbubbush.hello.mapper.HelloMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HelloService {
    @Autowired
    HelloMapper helloMapper;

    public List<Map<String, Object>> getMeberList () {
        return helloMapper.getMeberList();
    }

    public int insertMember (MemberDTO member) {
        return helloMapper.insertMember(member);
    }
    public int updateMember (MemberDTO member) {
        return helloMapper.updateMember(member);
    }
    public int deleteMember (MemberDTO member) {
        return helloMapper.deleteMember(member);
    }

}
