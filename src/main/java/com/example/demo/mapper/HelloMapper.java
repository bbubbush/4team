package com.example.demo.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface HelloMapper {
    public List<Map<String, Object>> getList();
}
