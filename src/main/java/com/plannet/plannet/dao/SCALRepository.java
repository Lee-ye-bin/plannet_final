package com.plannet.plannet.dao;

import com.plannet.plannet.entity.Member;
import com.plannet.plannet.entity.SCAL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SCALRepository extends JpaRepository<SCAL, Long> {
    SCAL findByUserId(Member member);
}
