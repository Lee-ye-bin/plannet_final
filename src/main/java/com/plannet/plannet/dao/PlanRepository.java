package com.plannet.plannet.dao;

import com.plannet.plannet.entity.Plan;
import com.plannet.plannet.vo.WriteDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<WriteDTO> findByUserId(String userId);
    List<WriteDTO> findByUserIdAndPlanChecked(String userId, int planChecked);
    void deleteByUserIdAndPlanDate(String userId, LocalDateTime date);
}
