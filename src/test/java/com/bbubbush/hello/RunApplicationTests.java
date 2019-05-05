package com.bbubbush.hello;

import com.bbubbush.hello.dto.MemberDTO;
import com.bbubbush.hello.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RunApplicationTests {
    @Autowired
    HelloService helloService;

    @Test
    public void 회원수조회() {
        System.out.println("멤버 리스트 사이즈 :: " + helloService.getMeberList().size());
    }

    @Test
    public void 회원추가() {
        MemberDTO newMember = new MemberDTO();
        newMember.setName("손주누");
        helloService.insertMember(newMember);
        System.out.println("멤버 리스트 사이즈 :: " + helloService.getMeberList().size());
    }

    @Test
    public void 회원삭제() {
        MemberDTO oldMember = new MemberDTO();
        oldMember.setNo(1);
        helloService.deleteMember(oldMember);
        System.out.println("멤버 리스트 사이즈 :: " + helloService.getMeberList().size());
    }

    @Test
    public void 회원이름변경() {
        MemberDTO oldMember = new MemberDTO();
        oldMember.setNo(1);
        oldMember.setName("최원락");
        System.out.println("기존 이름 :: " + helloService.getMeberList().get(0).get("NAME"));
        helloService.updateMember(oldMember);
        System.out.println("변경된 이름 :: " + helloService.getMeberList().get(0).get("NAME"));
    }

}
