package com.plannet.plannet.controller;

import com.plannet.plannet.service.BoardService;
import com.plannet.plannet.vo.BoardDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BoardController {
    private BoardService boardService;
    public BoardController(BoardService boardService) {this.boardService = boardService;}
    @GetMapping("/GetBoard/List")
    public ResponseEntity<List<BoardDTO>> boardList() {
        List<BoardDTO> list = boardService.getBoardList('1');
        return new ResponseEntity(list, HttpStatus.OK);
    }
}
