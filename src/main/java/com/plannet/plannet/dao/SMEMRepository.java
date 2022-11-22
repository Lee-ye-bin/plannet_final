package com.plannet.plannet.dao;

import com.plannet.plannet.entity.SMEM;
import com.plannet.plannet.entity.SMEMPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SMEMRepository extends JpaRepository<SMEM, SMEMPK> {
}
