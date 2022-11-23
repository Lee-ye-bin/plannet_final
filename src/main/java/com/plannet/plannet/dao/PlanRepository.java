package com.plannet.plannet.dao;

import com.plannet.plannet.entity.Plan;
import com.plannet.plannet.vo.PlanDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<PlanDTO> findByUserId(String userId);
    List<PlanDTO> findByUserIdAndPlanChecked(String userId, int planChecked);
}
