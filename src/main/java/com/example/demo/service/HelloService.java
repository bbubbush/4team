package com.example.demo.service;

import com.example.demo.mapper.HelloMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HelloService {
    @Autowired
    HelloMapper helloMapper;

    public List<Map<String, Object>> getList () {
        return helloMapper.getList();
    }

}
