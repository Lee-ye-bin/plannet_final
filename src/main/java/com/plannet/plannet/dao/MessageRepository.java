package com.plannet.plannet.dao;

import com.plannet.plannet.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
