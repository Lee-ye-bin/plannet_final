package com.plannet.plannet.controller;

import com.plannet.plannet.dao.BoardRepository;
import com.plannet.plannet.dao.MemberRepository;
import com.plannet.plannet.entity.Board;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class BoardControllerTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BoardRepository boardRepository;
    @Test
    @DisplayName("자유게시판 글 삭제 테스트")
    public void DeleteTest() {
        Board board = new Board();
        board.getBoardNo();
    }
}