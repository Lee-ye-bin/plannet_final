package com.plannet.plannet.dao;

import com.plannet.plannet.entity.Member;
import com.plannet.plannet.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findByUserId(Member userId);
    List<Plan> findByUserIdAndPlanChecked(Member userId, int planChecked);
    List<Plan> findByUserIdAndPlanDateOrderByPlanNoAsc(Member userId, LocalDate localDate);
    void deleteByUserId(Member member);
    void deleteByUserIdAndPlanDate(Member userId, LocalDate localDate);
    


}
