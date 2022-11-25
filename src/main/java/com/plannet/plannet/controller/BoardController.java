package com.plannet.plannet.controller;

import com.plannet.plannet.entity.Board;
import com.plannet.plannet.service.BoardService;
import com.plannet.plannet.vo.BoardDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/board")
@Slf4j
public class BoardController {
    // Service 로직 연결
    private BoardService boardService;
    public BoardController(BoardService boardService) { this.boardService = boardService; }

    // 전체 보드 리스트 불러오기
    @GetMapping("/list")
    // 전체조회기 때문에 boardList(@RequestParam) 으로 param 값을 받을 필요가 없음
    public ResponseEntity<List<BoardDTO>> boardList() {
        // 서비스를 다녀옴
        BoardDTO boardList = boardService.getBoardList();
        if(boardList.isOk()) {
            return new ResponseEntity(boardList.getBoardList(), HttpStatus.OK);
        } else return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    // 특정 보드넘버의 게시물 내용 불러오기 + 좋아요 수
    @GetMapping("/post_view")
    public ResponseEntity<List<BoardDTO>> postView(Long boardNo) {
        BoardDTO postView = boardService.getPostView(boardNo);
        return new ResponseEntity(postView, HttpStatus.OK);
    }

    // boardNo로 내가 해당 게시물에 좋아요를 눌렀는지 조회하기
    @GetMapping("/like_checked")
    public ResponseEntity<Integer> likeChecked(@RequestParam String id, Board boardNo) {
        boolean likeChecked = boardService.getLikeChecked(id, boardNo);
        if (likeChecked) {
            return new ResponseEntity(likeChecked, HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }

    // boardNo의 게시물을 내가 작성하지 않았으면 조회수 +1
    @GetMapping("/views_up")
    public ResponseEntity<Integer> viewsUp(@RequestParam Long boardNo, int views) {
        boolean viewsChecked = boardService.getViews(boardNo);
        if (viewsChecked) {
            return new ResponseEntity(viewsChecked, HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }

    // 자유게시판 글 삭제하기
    @GetMapping("/Board_delete")
    public ResponseEntity<Integer> boardDelete(@RequestParam Long boardNo) {
        boolean boardDelete = boardService.getboardDelete(boardNo);
        if (boardDelete) {
            return new ResponseEntity(boardDelete, HttpStatus.OK);
        } else {
            return new ResponseEntity(boardDelete, HttpStatus.BAD_REQUEST);
        }
    }
    
//    // 자유게시판 글 수정하기
//    @GetMapping("/BoardEdit")
//    public ResponseEntity<Integer> boardEdit(@RequestParam String id, int boardNo, String title, String detail) {
//        boolean boardEdit = boardService.getboardEdit(id, boardNo, title, detail);
//        if (boardEdit) {
//            return new ResponseEntity(boardEdit, HttpStatus.OK);
//        } else {
//            return new ResponseEntity(boardEdit, HttpStatus.BAD_REQUEST);
//        }
//    }
}