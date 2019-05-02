package com.bbubbush.hello.mapper;

import com.bbubbush.hello.dto.MemberDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface HelloMapper {
    @Select("SELECT * FROM member")
    public List<Map<String, Object>> getList();

    @Insert("INSERT INTO member (name) VALUES(#{name})")
    public int insertMember(MemberDTO member);

}
