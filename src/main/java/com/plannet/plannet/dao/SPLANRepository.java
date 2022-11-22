package com.plannet.plannet.dao;

import com.plannet.plannet.entity.SPLAN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SPLANRepository extends JpaRepository<SPLAN, Long> {
}
