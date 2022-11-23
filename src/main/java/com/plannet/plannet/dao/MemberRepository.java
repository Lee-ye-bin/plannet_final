package com.plannet.plannet.dao;

import com.plannet.plannet.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, String> {
    List<Member> findByIdAndPwd(String id, String pwd);

    Member findByEmail(String email);

    Member findByTell(String tell);
}
