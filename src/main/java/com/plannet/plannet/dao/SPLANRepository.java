package com.plannet.plannet.dao;

import com.plannet.plannet.entity.SCAL;
import com.plannet.plannet.entity.SPLAN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SPLANRepository extends JpaRepository<SPLAN, Long> {
    List<SPLAN> findByCalNo(SCAL scal);
    List<SPLAN> findByCalNoAndPlanChecked(SCAL scal, int planChecked);
}
