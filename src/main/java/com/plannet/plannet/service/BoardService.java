package com.plannet.plannet.service;

import com.plannet.plannet.dao.BoardRepository;
import com.plannet.plannet.dao.LikeCntRepository;
import com.plannet.plannet.dao.MemberRepository;
import com.plannet.plannet.dao.CommentsRepository;
import com.plannet.plannet.entity.Board;
import com.plannet.plannet.entity.Comments;
import com.plannet.plannet.entity.LikeCnt;
import com.plannet.plannet.entity.Member;
import com.plannet.plannet.vo.BoardDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.ExemptionMechanismException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.*;

// 의존성 주입을 받는다: 객체 생성 없이 사용할 수 있게 한다
@Service
@RequiredArgsConstructor
@Slf4j // log를 찍기 위한 어노테이션
public class BoardService {
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository; // 의존성 주입을 받음
    private final LikeCntRepository likeCntRepository; // 의존성 주입을 받음
    private final CommentsRepository commentsRepository;

    // 보드 목록 불러오기
    public BoardDTO getBoardList() {
        BoardDTO boardDTO = new BoardDTO();
        List<Map<String, Object>> boardList = new ArrayList<>();
        try {
            List<Board> boardData = boardRepository.findAllByOrderByBoardNoDesc();
            for (Board e : boardData) {
                Map<String, Object> board = new HashMap<>();
                board.put("boardNo", e.getBoardNo());
                // 익명체크 여부 확인 후 닉네임 넣기
                if(e.getIsChecked() == 0) {
                    board.put("nickname", e.getUserId().getNickname());
                } else board.put("nickname", "익명");
                board.put("title", e.getTitle());
                board.put("views", e.getViews());
                board.put("writeDate", e.getWriteDate());
                boardList.add(board);
            }
            boardDTO.setBoardList(boardList);
            boardDTO.setOk(true);
        } catch (Exception e) {
            boardDTO.setOk(false);
        }
        return boardDTO;
    }

    // 보드 넘버에 해당하는 글의 상세페이지 불러오기
    public BoardDTO getPostView(Long boardNo) {
        Board board = boardRepository.findById(boardNo).orElseThrow();
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardNo(boardNo);
        boardDTO.setTitle(board.getTitle());
        int isChecked = board.getIsChecked();
        boardDTO.setIsChecked(isChecked);
        if (isChecked == 0) boardDTO.setNickname(board.getUserId().getNickname());
        else boardDTO.setNickname("익명");
        boardDTO.setViews(board.getViews());
        boardDTO.setWriteDate(board.getWriteDate());
        boardDTO.setDetail(board.getDetail());
        boardDTO.setLikeCnt(likeCntRepository.countByBoardNo(board).intValue());
        return boardDTO;
    }

    // 내가 해당 게시물을 좋아요 눌렀는지 여부
    public boolean getLikeChecked(String id, Board boardNo) {
        Member member = memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        boolean CurrentLikeChecked = likeCntRepository.existsByUserIdAndBoardNo(member, boardNo);
        try {
            if (CurrentLikeChecked) likeCntRepository.deleteByUserIdAndBoardNo(member, boardNo);
            else {
                LikeCnt likeCnt = new LikeCnt();
                likeCnt.setUserId(member);
                likeCnt.setBoardNo(boardNo);
                likeCntRepository.save(likeCnt);
            }
            return CurrentLikeChecked;
        } catch (Exception e) {
            return false;
        }
    }

    // 조회수 +1
    public boolean getViews(Long boardNo) {
        Board board = boardRepository.findById(boardNo).orElseThrow();
        int CurrentViews = board.getViews() + 1;
        try {
            board.setViews(CurrentViews);
            boardRepository.save(board);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 자유게시판 글 작성하기
    public boolean writeBoard(String id, String title, String detail, int isChecked){
        Board board = new Board();
        board.setUserId(memberRepository.findById(id).orElseThrow());
        board.setTitle(title);
        board.setDetail(detail);
        board.setIsChecked(isChecked);
        board.setWriteDate(LocalDateTime.now());
        boardRepository.save(board);
        return true;
    }

    // 자유게시판 글 삭제하기
    public boolean boardDelete(Long boardNo) {
        try {
            boardRepository.deleteById(boardNo);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    // 자유게시판 글 수정하기
    public boolean boardEdit(String userId, Long boardNo, String title, String detail) {
        try{
            Board board = boardRepository.findById(boardNo).orElseThrow(EmptyStackException::new);
            board.setTitle(title);
            board.setDetail(detail);
            Board rst = boardRepository.save(board);
            log.warn(rst.toString());
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    // 자유게시판 댓글 작성하기
    public boolean getcommentsCreate(Long boardNo, String id, String detail) {
        Comments comments = new Comments();
        comments.setUserId(memberRepository.findById(id).orElseThrow());
        comments.setBoardNo(boardRepository.findById(boardNo).orElseThrow());
        comments.setDetail(detail);
        comments.setWriteDate(LocalDateTime.now());
        commentsRepository.save(comments);
        return true;
    }

    // 자유게시판 댓글 불러오기
    public BoardDTO commentsLoad (Integer boardNo) {
        BoardDTO boardDTO = new BoardDTO();
        try {
            List<Map<String, Object>> commentList = new ArrayList<>();
            Board board = boardRepository.findById((long)boardNo).orElseThrow(ExemptionMechanismException::new);
            List<Comments> data = commentsRepository.findByBoardNo(board);
            for (Comments e : data) {
                Map<String, Object> comment = new HashMap<>();
                comment.put("nickname", e.getUserId().getNickname());
                comment.put("detail", e.getDetail());
                comment.put("date", e.getWriteDate());
                commentList.add(comment);
            }
            boardDTO.setCommentList(commentList);
            boardDTO.setOk(true);
        } catch (Exception e) {
            boardDTO.setOk(false);
        }
        return boardDTO;
    }
}