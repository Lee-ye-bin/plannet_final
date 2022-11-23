package com.plannet.plannet.controller;

import com.plannet.plannet.dao.MemberRepository;
import com.plannet.plannet.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberControllerTest {
    @Autowired
    MemberRepository memberRepository;
    @Test
    @DisplayName("회원 가입 테스트")
    public  void regMemberTest() {
        for(int i =1; i<11; i++){
            Member member = new Member();
            member.setId("test123a"+i);
            member.setPwd("test1234"+i);
            member.setName("테스트"+i);
            member.setEmail("test0001@gmail.com"+i);
            member.setNickname("테스트11");
            member.setTel("010-1234-567"+i);
            member.setJoinDate(LocalDateTime.now());
            memberRepository.save(member);
        }
    }
}