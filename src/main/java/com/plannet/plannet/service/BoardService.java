package com.plannet.plannet.service;

import com.plannet.plannet.dao.BoardRepository;
import com.plannet.plannet.dao.LikeCntRepository;
import com.plannet.plannet.entity.Board;
import com.plannet.plannet.entity.LikeCnt;
import com.plannet.plannet.vo.BoardDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// 의존성 주입을 받는다: 객체 생성 없이 사용할 수 있게 한다
@Service
@Slf4j // log를 찍기 위한 어노테이션
public class BoardService {
    private BoardRepository boardRepository; // 의존성 주입을 받음
    private LikeCntRepository likeCntRepository; // 의존성 주입을 받음
    public BoardService(BoardRepository boardRepository) { this.boardRepository = boardRepository; }

    // 보드 목록 불러오기
    public List<BoardDTO> getBoardList() {
        List<BoardDTO> boardDTOS = new ArrayList<>();
        List<Board> boardList = boardRepository.findAll();
        for (Board e : boardList) {
            BoardDTO boardDTO = new BoardDTO();
            boardDTO.setBoardNo(e.getBoardNo());
            boardDTO.setId(e.getId().getId());
            boardDTO.setTitle(e.getTitle());
            boardDTO.setViews(e.getViews());
            boardDTO.setWriteDate(e.getWriteDate());
            boardDTO.setDetail(e.getDetail());
            boardDTO.setIsChecked(e.getIsChecked());
            // 얘는 테스트 중
            int likeCnt = (int)LikeCntRepository.countByBoardNo(e.getBoardNo());
        }
        return boardDTOS;
    }
}



//    int likeCnt = (int)LikeCntRepository.countByBoardNo(e.getBoardNo());
//            boardDTO.setLikeCnt(likeCnt);
//                    int like
//                    boardDTO.setIsLiked(e.getIsLiked());
//
//                    boardDTO.setCommentWriter(e.get);
//                    boardDTO.setCommentDate();
//                    boardDTO.setComment();
//                    boardDTOS.add(boardDTO);