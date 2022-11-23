package com.plannet.plannet.service;

import com.plannet.plannet.dao.BoardRepository;
import com.plannet.plannet.dao.LikeCntRepository;
import com.plannet.plannet.dao.MemberRepository;
import com.plannet.plannet.entity.Board;
import com.plannet.plannet.entity.LikeCnt;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
//@TestPropertySource(locations = "classpath:application-test.properties")
class BoardServiceTest {
    @Autowired // 자동연결
    MemberRepository memberRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    LikeCntRepository likeCntRepository;
    @Test
    @DisplayName("board 테이블")
    public void boardListTest() {
        for (int i = 1; i <= 10; i++) {
            Board board = new Board();
            board.setBoardNo(i);
            board.setUserId(memberRepository.findById("test00009").orElseThrow());
            board.setDetail("곰돌이사육사" + i);
            board.setIsChecked(0);
            board.setTitle("제목이다" + i);
            board.setViews(0);
            board.setWriteDate(LocalDateTime.now());
            boardRepository.save(board);
        }
    }

    // PK 관련부분 전부 수정해야 함
//    @Test
//    @DisplayName("likeCnt 테이블 생성")
//    public void likeCntListTest() {
//        for (int i = 1; i <= 5; i++) {
//            LikeCnt likeCnt = new LikeCnt();
//            likeCnt.setUserId(memberRepository.findById("test0000" + i).orElseThrow());
//            likeCnt.setBoardNo(boardRepository.findById(i).orElseThrow());
//            likeCntRepository.save(likeCnt);
//        }
//    }
}