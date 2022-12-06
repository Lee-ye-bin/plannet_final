package com.plannet.plannet.dao;

import com.plannet.plannet.entity.Member;
import com.plannet.plannet.entity.SCAL;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SCALRepository extends JpaRepository<SCAL, Long> {
    List<SCAL> findByUserId(Member member);
    void deleteByUserId(Member member);
}
