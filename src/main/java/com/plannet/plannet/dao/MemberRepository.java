package com.plannet.plannet.dao;

import com.plannet.plannet.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
