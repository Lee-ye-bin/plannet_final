package com.plannet.plannet.dao;

import com.plannet.plannet.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Slf4j
//@TestPropertySource(locations = "classpath:application-test.properties")
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Test
    @DisplayName("회원 가입 테스트")
    public void regMemberTest() {
        for(int i = 1; i <= 9; i++){
            Member member = new Member();
            member.setId("test0000"+i);
            member.setPwd("test0000!"+i);
            member.setName("테스트"+i);
            member.setEmail("test000"+i+"@gmail.com");
            member.setNickname("test"+i);
            member.setTel("010-1234-567"+i);
            member.setJoinDate(LocalDateTime.now());
            memberRepository.save(member);
        }
    }
    @Test
    @DisplayName("로그인 테스트")
    public void loginTest() {
        Member member = new Member();
        boolean check =memberRepository.findByIdAndPwd("test00001","test0000!1");
        if(check) log.warn("로그인 성공");
        else log.warn("로그인 실패");
    }
}