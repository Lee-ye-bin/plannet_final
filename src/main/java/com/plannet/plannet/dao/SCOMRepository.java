package com.plannet.plannet.dao;

import com.plannet.plannet.entity.SCOM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SCOMRepository extends JpaRepository<SCOM, Long> {
}
