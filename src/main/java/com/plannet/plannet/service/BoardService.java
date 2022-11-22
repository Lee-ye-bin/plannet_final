package com.plannet.plannet.service;

import com.plannet.plannet.dao.BoardRepository;
import com.plannet.plannet.entity.Board;
import com.plannet.plannet.vo.BoardDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BoardService {
    private BoardRepository boardRepository;
    public BoardService(BoardRepository boardRepository) {this.boardRepository = boardRepository;}

    public List<BoardDTO> getBoardList(int boardNo) {
        List<BoardDTO> boardDTOS = new ArrayList<>();
        List<Board> boardList = boardRepository.findByBoardNo(boardNo);
        for(Board e : boardList) {
            BoardDTO boardDTO = new BoardDTO();
            boardDTO.setBoardNo(e.getBoardNo());
            boardDTO.setTitle(e.getTitle());
            // boardDTO.setId(e.getId());
            boardDTO.setViews(e.getViews());
            boardDTO.setWriteDate(LocalDateTime.now());
            boardDTOS.add(boardDTO);
        }
        return boardDTOS;
    }
}
