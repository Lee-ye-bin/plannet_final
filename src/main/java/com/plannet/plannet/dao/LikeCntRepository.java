package com.plannet.plannet.dao;

import com.plannet.plannet.entity.Board;
import com.plannet.plannet.entity.LikeCnt;
import com.plannet.plannet.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// 엔티티를 만들었으니 DB에 접근할 수 있는 Repository를 만든다
// 요청과 응답만 처리
// JpaRepository<테이블명, 프라이머리키에 대한 데이터 형>
public interface LikeCntRepository extends JpaRepository<LikeCnt, Long> {
    Long countByBoardNo(Board boardNo);
    boolean existsByUserIdAndBoardNo(Member userId, Board boardNo);
    void deleteByUserIdAndBoardNo(Member member, Board boardNo);
    void deleteByBoardNo(Board board);
    void deleteByUserId(Member member);
}
