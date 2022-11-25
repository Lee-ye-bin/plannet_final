package com.plannet.plannet.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter @Setter
public class HomeDTO {
    private List<List<Map<String, Object>>> weekPlan;
    private List<Set<LocalDateTime>> calDot;
}
