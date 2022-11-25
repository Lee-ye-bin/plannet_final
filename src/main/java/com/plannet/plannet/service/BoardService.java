package com.plannet.plannet.service;

import com.plannet.plannet.dao.BoardRepository;
import com.plannet.plannet.dao.LikeCntRepository;
import com.plannet.plannet.dao.MemberRepository;
import com.plannet.plannet.entity.Board;
import com.plannet.plannet.entity.LikeCnt;
import com.plannet.plannet.entity.Member;
import com.plannet.plannet.vo.BoardDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

// 의존성 주입을 받는다: 객체 생성 없이 사용할 수 있게 한다
@Service
@RequiredArgsConstructor
@Slf4j // log를 찍기 위한 어노테이션
public class BoardService {
    private MemberRepository memberRepository;
    private BoardRepository boardRepository; // 의존성 주입을 받음
    private LikeCntRepository likeCntRepository; // 의존성 주입을 받음
    public BoardService(BoardRepository boardRepository, LikeCntRepository likeCntRepository, MemberRepository memberRepository) {
        this.boardRepository = boardRepository;
        this.likeCntRepository = likeCntRepository;
        this.memberRepository = memberRepository;
    }

    // 보드 목록 불러오기
    public List<BoardDTO> getBoardList() {
        List<BoardDTO> boardDTOS = new ArrayList<>();
        List<Board> boardList = boardRepository.findAll();
        for (Board e : boardList) {
            BoardDTO boardDTO = new BoardDTO();
            boardDTO.setBoardNo(e.getBoardNo());
            boardDTO.setId(e.getUserId().getId());
            boardDTO.setTitle(e.getTitle());
            boardDTO.setViews(e.getViews());
            boardDTO.setWriteDate(e.getWriteDate());
            boardDTO.setDetail(e.getDetail());
            boardDTO.setIsChecked(e.getIsChecked());
        }
        return boardDTOS;
    }

    // LikeCnt 테이블에서 특정 'boardNo'을 기준으로 레코드 개수 세기
    public long getLikeCnt(Board boardNo) {
        Long likeCntList = likeCntRepository.countByBoardNo(boardNo);
        return likeCntList;
    }

    // 내가 해당 게시물을 좋아요 눌렀는지 여부
    public boolean getLikeChecked(String id, Board boardNo) {
        Member member = memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        boolean CurrentLikeChecked = likeCntRepository.existsByUserIdAndBoardNo(member, boardNo);
        if (CurrentLikeChecked) likeCntRepository.deleteByUserIdAndBoardNo(member, boardNo);
        else {
            LikeCnt likeCnt = new LikeCnt();
            likeCnt.setUserId(member);
            likeCnt.setBoardNo(boardNo);
            likeCntRepository.save(likeCnt);
        }
        return CurrentLikeChecked;
    }

    // 자유게시판 글 삭제하기
    public boolean getboardDelete(Long boardNo) {
        try {
            boardRepository.deleteById(boardNo);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    // 자유게시판 글 수정하기
    public boolean getboardEdit(String id, int boardNo, String title, String detail) {
        try {
            boardRepository.findById(id, boardNo, title, detail);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
//    // 자유게시판 댓글 작성하기
//    public boolean getboardCommentCreate(int boardNo, String id, String detail) {
//        try {
//            boardRepository.findById(boardNo, id, detail);
//        }
//    }
}

////     얘는 테스트 중
//   int likeCnt = (int)LikeCntRepository.countByBoardNo(e.getBoardNo());
//    int likeCnt = (int)LikeCntRepository.countByBoardNo(e.getBoardNo());
//            boardDTO.setLikeCnt(likeCnt);
//                    int like
//                    boardDTO.setIsLiked(e.getIsLiked());
//
//                    boardDTO.setCommentWriter(e.get);
//                    boardDTO.setCommentDate();
//                    boardDTO.setComment();
//                    boardDTOS.add(boardDTO);