package com.plannet.plannet.dao;

import com.plannet.plannet.entity.Member;
import com.plannet.plannet.entity.SMEM;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SMEMRepository extends JpaRepository<SMEM, Long> {
    List<SMEM> findByUserId(Member userId);
    void deleteByUserId(Member member);
}
