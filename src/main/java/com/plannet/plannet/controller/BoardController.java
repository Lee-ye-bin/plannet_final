package com.plannet.plannet.controller;

import com.plannet.plannet.entity.Board;
import com.plannet.plannet.service.BoardService;
import com.plannet.plannet.vo.BoardDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/board")
@Slf4j
public class BoardController {
    // Service 로직 연결
    private final BoardService boardService;
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

    @PostMapping("/write")
    public ResponseEntity<Boolean> writeBoard(@RequestBody Map<String, String> boardWriteDate) {
        String id = boardWriteDate.get("id");
        String title = boardWriteDate.get("title");
        String detail = boardWriteDate.get("detail");
        int isChecked = Integer.parseInt(boardWriteDate.get("isChecked"));

        boolean result = boardService.writeBoard(id, title, detail, isChecked);
        if(result) {
            return new ResponseEntity(true, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
    }
    // 자유게시판 글 삭제하기
    @PostMapping("/delete")
    public ResponseEntity<Boolean> boardDelete(@RequestParam Long boardNo) {
        boolean result = boardService.boardDelete(boardNo);
        if(result) return new ResponseEntity(true, HttpStatus.OK);
        else return new ResponseEntity(false, HttpStatus.OK);
    }
    // 자유게시판 글 수정
    @PostMapping("edit")
    public ResponseEntity<Boolean> boardEdit(@RequestBody Map<String, String> boardEdit) {
        String userId = boardEdit.get("id");
        Long boardNo = Long.parseLong(boardEdit.get("num"));
        String title = boardEdit.get("title");
        String detail = boardEdit.get("detail");

        boolean result = boardService.boardEdit(userId, boardNo, title, detail);
        if(result) {
            return new ResponseEntity(true, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(false, HttpStatus.OK);
        }
    }
    // 자유게시판 댓글 작성하기
    @GetMapping("/comment/write")
    public ResponseEntity<Integer> boardCommentsCreate(@RequestParam Long boardNo, String id, String detail) {
        boolean boardCommentsCreate = boardService.getcommentsCreate(boardNo, id, detail);
        if (boardCommentsCreate) {
            return new ResponseEntity(boardCommentsCreate, HttpStatus.OK);
        } else {
            return new ResponseEntity(boardCommentsCreate, HttpStatus.OK);
        }
    }

    // 자유게시판 댓글 불러오기
    @PostMapping("/comment/load")
    public ResponseEntity<List<Map<String, Object>>> boardCommentsLoad(@RequestBody Map<Integer, Integer> boardNo) {
        int num = boardNo.get("num");
        BoardDTO boardDTO = boardService.commentsLoad(num);
        if(boardDTO.isOk()) {
            List<Map<String, Object>> commentList = boardDTO.getCommentList();
            return new ResponseEntity(commentList, HttpStatus.OK);
        } else return new ResponseEntity(null, HttpStatus.OK);
    }
}