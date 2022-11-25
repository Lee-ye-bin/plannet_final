package com.plannet.plannet.dao;

import com.plannet.plannet.entity.Member;
import com.plannet.plannet.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findByUserId(Member userId);
    List<Plan> findByUserIdAndPlanChecked(Member userId, int planChecked);
    void deleteByUserIdAndPlanDate(Member userId, LocalDate date);
    List<Plan> findByUserIdAndPlanDateOrderByPlanNoAsc(Member member, LocalDate localDate);
}
