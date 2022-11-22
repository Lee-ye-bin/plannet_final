package com.plannet.plannet.service;

import com.plannet.plannet.dao.BoardRepository;
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

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<BoardDTO> getMemberList() {
        List<MemberDTO> memberDTOS = new ArrayList<>();
        List<Member> memberList = memberRepository.findAll();
        for (Member e : memberList) {
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setUser(e.getUserId());
            memberDTO.setPwd(e.getPwd());
            memberDTO.setName(e.getName());
            memberDTO.setEmail(e.getEmail());
            memberDTO.setGrade("VIP");
            memberDTOS.add(memberDTO);
        }
        return memberDTOS;
    }
}
