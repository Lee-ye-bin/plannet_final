//package com.plannet.plannet.service;
//
//import com.plannet.plannet.dao.LikeCntRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Slf4j
//@TestPropertySource(locations = "classpath:application-test.properties")
//class BoardServiceTest {
//    @Autowired // 자동연결
//    LikeCntRepository likeCntRepository;
//    @Test
//    @DisplayName("좋아요 수 세기 테이블")
//    public void regMemberTest() {
//        for (int i = 1; i <= 10; i++) {
//            Member member = new Member();
//            member.setUserId("JKS2024" + i);
//            member.setPwd("SPH88250");
//            member.setName("곰돌이사육사" + i);
//            member.setEmail("jks2024@gmail.com" + i);
//            member.setRegData(LocalDateTime.now());
//            memberRepository.save(member);
//        }
//    }
//
//}