package com.bbubbush.hello.mapper;

import com.bbubbush.hello.dto.MemberDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface HelloMapper {
    @Select("SELECT * FROM member")
    List<Map<String, Object>> getMeberList();

    @Insert("INSERT INTO member (name) VALUES(#{name})")
    int insertMember(MemberDTO member);

    @Update("UPDATE member SET name = #{name} WHERE no = #{no}")
    int updateMember(MemberDTO member);

    @Delete("DELETE FROM member WHERE no = #{no}")
    int deleteMember(MemberDTO member);

}
