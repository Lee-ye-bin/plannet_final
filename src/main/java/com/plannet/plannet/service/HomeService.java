package com.plannet.plannet.service;

import com.plannet.plannet.dao.MemberRepository;
import com.plannet.plannet.dao.PlanRepository;
import com.plannet.plannet.dao.QuoteRepository;
import com.plannet.plannet.entity.Member;
import com.plannet.plannet.entity.Plan;
import com.plannet.plannet.vo.HomeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class HomeService {
    private final MemberRepository memberRepository;
    private final QuoteRepository quoteRepository;
    private final PlanRepository planRepository;

    public HomeDTO homeList(String id) {
        HomeDTO homeDTO = new HomeDTO();
        try{
            Member member = memberRepository.findById(id).orElseThrow();
            LocalDate[] weekDay = {
                    LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)),
                    LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)),
                    LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.TUESDAY)),
                    LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.WEDNESDAY)),
                    LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY)),
                    LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY)),
                    LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY))
            };
            List<List<Map<String, Object>>> weekPlan = new ArrayList<>();
            for(int i = 0; i < 7; i++) {
                List<Map<String, Object>> dayPlan = new ArrayList<>();
                List<Plan> dayPlanOrigin = planRepository.findByUserIdAndPlanDateOrderByPlanNoAsc(member, weekDay[i]);
                for(Plan e : dayPlanOrigin) {
                    Map<String, Object> plan = new HashMap<>();
                    plan.put("no", e.getPlanNo());
                    plan.put("plan", e.getPlan());
                    plan.put("checked", e.getPlanChecked());
                    dayPlan.add(plan);
                }
                weekPlan.add(dayPlan);
            }
            homeDTO.setWeekPlan(weekPlan);

            // planMark
            List<Set<LocalDate>> planMark = new ArrayList<>();
            for(int i = 0; i < 2; i++) {
                Set<LocalDate> planDot = new HashSet<>();
                List<Plan> plan = planRepository.findByUserIdAndPlanChecked(member, i);
                for(Plan e : plan) {
                    planDot.add(e.getPlanDate());
                }
                planMark.add(planDot);
            }
            homeDTO.setPlanMark(planMark);

            // memoLoad
            homeDTO.setMemo(member.getMemo());

            // quoteLoad
            int randomNum = (int) (Math.random() * ((int) quoteRepository.count() + 1));
            homeDTO.setQuote(quoteRepository.findById(randomNum).orElseThrow().getQuote());
            homeDTO.setOk(true);
        } catch (Exception e){
            homeDTO.setOk(false);
        }
        return homeDTO;
    }
}
