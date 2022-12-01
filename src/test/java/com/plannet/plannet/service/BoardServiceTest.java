package com.plannet.plannet.service;

import com.plannet.plannet.dao.*;
import com.plannet.plannet.entity.Board;
import com.plannet.plannet.entity.Comments;
import com.plannet.plannet.entity.LikeCnt;
import com.plannet.plannet.entity.Member;
import com.plannet.plannet.vo.BoardDTO;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
@Rollback(value = false)
@Slf4j

class BoardServiceTest {
    @Autowired // 자동연결
    MemberRepository memberRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    LikeCntRepository likeCntRepository;
    @Autowired
    CommentsRepository commentsRepository;
    @PersistenceContext
    EntityManager em;
    @Test
    @DisplayName("board 테이블")
    public void boardListTest() {
        for (int i = 1; i <= 10; i++) {
            Board board = new Board();
            board.setUserId(memberRepository.findById("test_id_1").orElseThrow());
            board.setDetail("곰돌이사육사" + i);
            board.setIsChecked(0);
            board.setTitle("제목이다" + i);
            board.setViews(0);
            board.setWriteDate(LocalDateTime.now());
            boardRepository.save(board);
        }
    }

    @Test
    @DisplayName("comments 테이블")
    public void commentsListTest() {
        for (int i = 1; i <= 10; i ++) {
            Comments comments = new Comments();
            comments.setBoardNo(boardRepository.findById((long) i).orElseThrow());
            comments.setUserId(memberRepository.findById("test_id_3").orElseThrow());
            comments.setWriteDate(LocalDateTime.now());
            comments.setDetail("댓글이다" + i+1);
            commentsRepository.save(comments);
        }
    }

//    @Test
//    @DisplayName("likeCnt 테이블 생성")
//    public void likeListTest() {
//        for (int i = 131; i <= 136; i++) {
//            LikeCnt likeCnt = new LikeCnt();
//            likeCnt.setUserId(memberRepository.findById("test_id_"+4).orElseThrow());
//            likeCnt.setBoardNo(boardRepository.findById((long)i).orElseThrow());
//            likeCntRepository.save(likeCnt);
//        }
//    }

//    @Test
//    @DisplayName("likeCnt 테스트, 해당 게시물에 좋아요 수가 몇인지")
//    public void likeCntTest() {
//        Board board = boardRepository.findById((long)131).orElseThrow(EntityNotFoundException::new);
//        long likeCnt = likeCntRepository.countByBoardNo(board);
//        System.out.println(likeCnt);
//    }
//
//    @Test
//    @DisplayName("likeChecked 테스트, 내가 해당 게시물에 좋아요를 눌렀는지 누르지 않았는지")
//    public void likeCheckedTest() {
//        Member member = memberRepository.findById("test_id_3").orElseThrow(EntityNotFoundException::new);
//        Board board = boardRepository.findById((long)131).orElseThrow(EntityNotFoundException::new);
//        boolean CurrentLikeChecked = likeCntRepository.existsByUserIdAndBoardNo(member, board);
//        if (CurrentLikeChecked) {
//            likeCntRepository.deleteByUserIdAndBoardNo(member, board);
//        } else {
//            LikeCnt likeCnt = new LikeCnt();
//            likeCnt.setUserId(member);
//            likeCnt.setBoardNo(board);
//            likeCntRepository.save(likeCnt);
//        }
//    }
//
//    @Test
//    @DisplayName("postView 불러오기 테스트")
//    public void postViewTest() {
//        Board board = boardRepository.findById((long) 131).orElseThrow();
//        BoardDTO boardDTO = new BoardDTO();
//        boardDTO.setBoardNo((long) 131);
//        boardDTO.setTitle(board.getTitle());
//        boardDTO.setNickname(board.getUserId().getNickname());
//        boardDTO.setViews(board.getViews());
//        boardDTO.setWriteDate(board.getWriteDate());
//        boardDTO.setDetail(board.getDetail());
//        boardDTO.setIsChecked(board.getIsChecked());
//        boardDTO.setLikeCnt(likeCntRepository.countByBoardNo(board).intValue());
//    }
//
//    public boolean writeBoard(String id, String title, String detail, int isChecked){
//        Board board = new Board();
//        board.setUserId(memberRepository.findById(id).orElseThrow());
//        board.setTitle(title);
//        board.setDetail(detail);
//        board.setIsChecked(isChecked);
//        board.setWriteDate(LocalDateTime.now());
//        boardRepository.save(board);
//        return true;
//    }
//
//    @Test
//    @DisplayName("comments 불러오기 테스트")
//    public void commentsLoadTest() {
//        Board board = boardRepository.findById((long) 131).orElseThrow();
//        BoardDTO boardDTO = new BoardDTO();
//        boardDTO.setBoardNo((long) 131);
//        boardDTO.setCommentWriter(board.getUserId().getNickname());
//        boardDTO.setCommentDate(LocalDateTime.now());
//        boardDTO.setCommentDetail(board.getDetail());
//    }
//    public boolean writeComments(int boardNo, String id, String detail) {
//        Comments comments = new Comments();
//        comments.setBoardNo(boardRepository.findById((long)(130)).orElseThrow());
//        comments.setUserId(memberRepository.findById("test_id_1").orElseThrow());
//        comments.setDetail(detail);
//        comments.setWriteDate(LocalDateTime.now());
//        commentsRepository.save(comments);
//        return true;
//    }
}