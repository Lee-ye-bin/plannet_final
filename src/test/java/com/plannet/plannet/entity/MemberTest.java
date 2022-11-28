package com.plannet.plannet.entity;

import com.plannet.plannet.dao.*;
import com.plannet.plannet.vo.HomeDTO;
import com.plannet.plannet.vo.MemberDTO;
import com.plannet.plannet.vo.ShareDTO;
import com.plannet.plannet.vo.WriteDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@SpringBootTest
@Transactional
@Slf4j
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PlanRepository planRepository;
    @Autowired
    SCALRepository scalRepository;
    @Autowired
    SMEMRepository smemRepository;
    @Autowired
    SPLANRepository splanRepository;

    @Test
    @Rollback(value = false)
    @DisplayName("멤버 인서트 테스트")
    public void createData() {
        //Member
        for(int i = 1; i < 11; i++) {
            Member member = new Member();
            member.setId("test_id_" + i);
            member.setPwd("test_pwd_" + i);
            member.setName("이름" + i);
            member.setNickname("nickname" + i);
            member.setEmail("test_" + i + "@gmail.com");
            member.setTel("010-0000-" + i);
            member.setJoinDate(LocalDateTime.now());
            member.setUserCode((int) (Math.floor(Math.random() * 9999) + 1));

            memberRepository.save(member);
        }
        //plan
        for(int i = 1; i < 11; i++) {
            Member member = memberRepository.findById("test_id_" + i).orElseThrow();
            for(int j = 1; j < 11; j++) {
                Plan plan = new Plan();
                plan.setUserId(member);
                plan.setPlan(i + "의 " + j + "번째 일정");
                plan.setPlanDate(LocalDate.now());
                if(j % 2 == 1) plan.setPlanChecked(1);
                else plan.setPlanChecked(0);
                planRepository.save(plan);
            }
        }
        //scal
        for(int i = 1; i < 6; i++){
            SCAL scal = new SCAL();
            scal.setCalName(i + "번의 공유 캘린더");
            scal.setCalMemo(i + "번째 공유 캘린더의 메모입니다");
            Member member = memberRepository.findById("test_id_" + (i*2)).orElseThrow();
            scal.setUserId(member);
            scalRepository.save(scal);
        }
        //smem
        for(int i = 1; i < 6; i++) {
            SMEM smemOwner = new SMEM();
            SCAL scal = scalRepository.findById((long) (100 + i)).orElseThrow(EntityNotFoundException::new);
            smemOwner.setCalNo(scal);
            smemOwner.setUserId(scal.getUserId());
            smemOwner.setIsOwner(1);
            smemRepository.save(smemOwner);
            for(int j = 1; j < 4; j++) {
                SMEM smem = new SMEM();
                smem.setCalNo(scal);
                Member member = memberRepository.findById("test_id_" + (i + j)).orElseThrow();
                smem.setUserId(member);
                smem.setIsOwner(0);
                smemRepository.save(smem);
            }
        }
        // splan
        for(int i = 1; i < 6; i++){
            SPLAN splan = new SPLAN();
            Member owner = memberRepository.findById("test_id_2").orElseThrow();
            SCAL scal = scalRepository.findByUserId(owner);
            splan.setCalNo(scal);
            splan.setPlanDate(LocalDateTime.now());
            Member member = memberRepository.findById("test_id_" + i).orElseThrow();
            splan.setUserId(member);
            if(i % 2 == 0) splan.setPlanChecked(1);
            else splan.setPlanChecked(0);
            splan.setPlan("101번 캘린더의" + i + "번째 일정입니다");
            splanRepository.save(splan);
        }
    }

    // 사용자 정보&개인 일정 달성률/공유캘린더정보 불러오기
    public void navList (String userId) {
        List<MemberDTO> navList = new ArrayList<>();
        MemberDTO memberDTO = new MemberDTO();
        Member member = memberRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        memberDTO.setNickname(member.getNickname());
        memberDTO.setId(member.getId());
        memberDTO.setUserCode(member.getUserCode());
        memberDTO.setProfile(member.getProfile());
        memberDTO.setEmail(member.getEmail());
        memberDTO.setSns(member.getSNS());
        memberDTO.setTel(member.getTel());
        memberDTO.setProImg(member.getProImg());

        //개인 일정 달성률 구하기
        List<WriteDTO> personalTotal = planRepository.findByUserId(userId);
        List<WriteDTO> personalEnd = planRepository.findByUserIdAndPlanChecked(userId, 1);
        int personalTotalCnt = 0; // 총 일정 갯수
        int personalEndCnt = 0; // 완료된 일정 갯수
        for(WriteDTO e : personalTotal) {
            personalTotalCnt++;
        }
        for(WriteDTO e : personalEnd) {
            personalEndCnt++;
        }
        memberDTO.setPes(personalTotalCnt * 100 / personalEndCnt);


    } // 테스트 끝 서비스로 옮겼음. 일부 테스트에 구현되어있지 않음

    @Test
    @Rollback(value = false)
    @DisplayName("일주일 일정")
    public void weekList() {
        String id = "test_id_1";
        Member member = memberRepository.findById(id).orElseThrow();
        HomeDTO homeDTO = new HomeDTO();

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
        System.out.println(weekPlan);
    }
    @Test
    @Rollback(value = false)
    @DisplayName("달력 dot")
    public void calDot() {

    }
}