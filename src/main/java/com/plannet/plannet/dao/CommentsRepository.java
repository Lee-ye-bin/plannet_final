package com.plannet.plannet.dao;

import com.plannet.plannet.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, String> {
}
