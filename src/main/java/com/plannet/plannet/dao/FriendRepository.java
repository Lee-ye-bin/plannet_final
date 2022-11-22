package com.plannet.plannet.dao;

import com.plannet.plannet.entity.Friend;
import com.plannet.plannet.entity.FriendPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, FriendPK> {
}
