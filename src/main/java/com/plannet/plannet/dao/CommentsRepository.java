package com.plannet.plannet.dao;

import com.plannet.plannet.entity.Board;
import com.plannet.plannet.entity.Comments;
import com.plannet.plannet.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface CommentsRepository extends JpaRepository<Comments, String> {
    List<Comments> findByBoardNo(Board board);
    Comments findByUserId(String userId);
    void deleteByBoardNo(Board board);
    void deleteByUserId (Comments comments);
}
