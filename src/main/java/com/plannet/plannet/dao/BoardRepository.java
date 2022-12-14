package com.plannet.plannet.dao;

import com.plannet.plannet.entity.Board;
import com.plannet.plannet.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// 엔티티를 만들었으니 DB에 접근할 수 있는 Repository를 만든다
// 요청과 응답만 처리
// JpaRepository<테이블명, 프라이머리키에 대한 데이터 형>
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByBoardNoDesc();
    List<Board> findByUserId(Member id);
    void deleteByUserId(Member member);
}

