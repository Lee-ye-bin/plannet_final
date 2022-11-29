package com.plannet.plannet.dao;

import com.plannet.plannet.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, String> {
    List<Member> findByIdAndPwd(String id, String pwd);
    Member findByEmail(String email);
    Member findByTel(String tel);
    Member findByNameAndEmail(String name, String email);
    Member findByIdAndEmail(String id, String email);
//    void deleteByUserId(String id);
}
