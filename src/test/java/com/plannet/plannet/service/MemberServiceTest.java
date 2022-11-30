//package com.plannet.plannet.service;
//
//import com.plannet.plannet.dao.*;
//import com.plannet.plannet.entity.*;
//import com.plannet.plannet.vo.BoardDTO;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityNotFoundException;
//import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Map;
//
//@SpringBootTest
//@Transactional
//@Rollback(value = false)
//@Slf4j
//
//class MemberServiceTest {
//    @Autowired // 자동연결
//    MemberRepository memberRepository;
//    @Autowired
//    BoardRepository boardRepository;
//    @Autowired
//    LikeCntRepository likeCntRepository;
//    @Autowired
//    CommentsRepository commentsRepository;
//    @Autowired
//    DiaryRepository diaryRepository;
//    @Autowired
//    PlanRepository planRepository;
//    @PersistenceContext
//    EntityManager em;
//    @Test
//    @DisplayName("맴버및 보드 생성")
//    public void createData() {
//        //Member
//        for (int i = 1; i < 11; i++) {
//            Member member = new Member();
//            member.setId("test_id_" + i);
//            member.setPwd("test_pwd_" + i);
//            member.setName("이름" + i);
//            member.setNickname("nickname" + i);
//            member.setEmail("test_" + i + "@gmail.com");
//            member.setTel("010-0000-" + i);
//            member.setJoinDate(LocalDateTime.now());
//            member.setUserCode((int) (Math.floor(Math.random() * 9999) + 1));
//            memberRepository.save(member);
//        }
//        //plan
//        for (int i = 1; i < 11; i++) {
//            Member member = memberRepository.findById("test_id_" + i).orElseThrow();
//            for (int j = 1; j < 11; j++) {
//                Plan plan = new Plan();
//                plan.setUserId(member);
//                plan.setPlan(i + "의 " + j + "번째 일정");
//                plan.setPlanDate(LocalDate.now());
//                if (j % 2 == 1) plan.setPlanChecked(1);
//                else plan.setPlanChecked(0);
//                planRepository.save(plan);
//            }
//        }
//    }
//    @Test
//    @DisplayName("board 테이블")
//    public void boardListTest() {
//        for (int i = 1; i <= 10; i++) {
//            Board board = new Board();
//            board.setUserId(memberRepository.findById("test_id_1").orElseThrow());
//            board.setDetail("곰돌이사육사" + i);
//            board.setIsChecked(0);
//            board.setTitle("제목이다" + i);
//            board.setViews(0);
//            board.setWriteDate(LocalDateTime.now());
//            boardRepository.save(board);
//        }
//    }
//
//    @Test
//    @DisplayName("comments 테이블")
//    public void commentsListTest() {
//        for (int i = 1; i <= 10; i ++) {
//            Comments comments = new Comments();
//            // comments.setCommentNo(commentsRepository.findById().
//            comments.setBoardNo(boardRepository.findById((long)(130 + i)).orElseThrow());
//            comments.setUserId(memberRepository.findById("test_id_1").orElseThrow());
//            comments.setWriteDate(LocalDateTime.now());
//            comments.setDetail("댓글이다" + i);
//            commentsRepository.save(comments);
//        }
//    }
//
//    @Test
//    @DisplayName("likeCnt 테이블 생성")
//    public void likeListTest() {
//        for (int i = 131; i <= 136; i++) {
//            LikeCnt likeCnt = new LikeCnt();
//            likeCnt.setUserId(memberRepository.findById("test_id_" + 4).orElseThrow());
//            likeCnt.setBoardNo(boardRepository.findById((long)i).orElseThrow());
//            likeCntRepository.save(likeCnt);
//        }
//    }
//
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
//    @Test
//    @DisplayName("Member Delete Test")
//    public void memberDelete(String id) {
//        id = "test_id_1";
//
//
//
//    }
//
//}