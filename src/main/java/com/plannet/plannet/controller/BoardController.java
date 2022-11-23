package com.plannet.plannet.controller;

import com.plannet.plannet.service.BoardService;
import com.plannet.plannet.vo.BoardDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
public class BoardController {
    // Service 로직 연결
    private BoardService boardService;
    public BoardController(BoardService boardService) { this.boardService = boardService; }

    // 전체 보드 리스트 불러오기
    @GetMapping("/Board/list")
    // 전체조회기 때문에 boardList(@RequestParam) 으로 param 값을 받을 필요가 없음
    public ResponseEntity<List<BoardDTO>> boardList() {
        // 서비스를 다녀옴, 결과를 리스트로 받음
        List<BoardDTO> list = boardService.getBoardList();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    // boardNo로 해당 게시물의 좋아요 수 조회하기
    @GetMapping("/Board/likeCnt")
    public ResponseEntity<Integer> boardList(@RequestParam int boardNo) {
        long likeCnt = boardService.getLikeCnt(boardNo);
        return new ResponseEntity(likeCnt, HttpStatus.OK);
    }

    // boardNo로 내가 해당 게시물에 좋아요를 눌렀는지 조회하기
}