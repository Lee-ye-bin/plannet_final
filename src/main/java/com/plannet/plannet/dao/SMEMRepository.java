package com.plannet.plannet.dao;

import com.plannet.plannet.entity.SMEM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SMEMRepository extends JpaRepository<SMEM, Long> {
}
