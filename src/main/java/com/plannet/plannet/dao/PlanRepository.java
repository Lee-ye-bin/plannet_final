package com.plannet.plannet.dao;

import com.plannet.plannet.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
