package com.plannet.plannet.service;

import com.plannet.plannet.dao.DiaryRepository;
import com.plannet.plannet.dao.MemberRepository;
import com.plannet.plannet.dao.PlanRepository;
import com.plannet.plannet.entity.Diary;
import com.plannet.plannet.entity.Member;
import com.plannet.plannet.entity.Plan;
import com.plannet.plannet.vo.WriteDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class WriteService {
    private MemberRepository memberRepository;
    private DiaryRepository diaryRepository;
    private PlanRepository planRepository;

    public WriteService(MemberRepository memberRepository, DiaryRepository diaryRepository, PlanRepository planRepository) {
        this.memberRepository = memberRepository;
        this.diaryRepository = diaryRepository;
        this.planRepository = planRepository;
    }
    // 일정 저장
    public boolean writeSave(String userId, LocalDate date, List<Map<String, Object>> plan, String diary) {
        // 회원 정보가 담긴 객체 가져옴
        Member member = memberRepository.findById(userId).orElseThrow(EmptyStackException::new);

        // plan 저장
        for(Map<String, Object> p : plan) {
            if(p.get("deleted").equals("false")) {
                Plan plans = new Plan();
                plans.setUserId(member);
                plans.setPlanDate(date);
                plans.setPlanNo(Long.parseLong((String)p.get("key")));
                if(p.get("checked").equals(true)) plans.setPlanChecked(1);
                else plans.setPlanChecked(0);
                plans.setPlan((String)p.get("text"));
                Plan rst = planRepository.save(plans);
                log.warn(rst.toString());
            }
        }
        // diary 업데이트
        List<Diary> diaryList = diaryRepository.findByUserIdAndDiaryDate(member, date);
            Diary diaries = new Diary();
            diaries.setDiary(diary);
            diaries.setUserId(member);
            diaries.setDiaryDate(date);
            Diary rst = diaryRepository.save(diaries);
            log.warn(rst.toString());
        return true;
    }
}
