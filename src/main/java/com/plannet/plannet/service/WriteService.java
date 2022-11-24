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

import java.time.LocalDateTime;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class WriteService {
    private MemberRepository memberRepository;
    private DiaryRepository diaryRepository;
    private PlanRepository planRepository;

    public WriteService(DiaryRepository diaryRepository, PlanRepository planRepository) {
        this.diaryRepository = diaryRepository;
        this.planRepository = planRepository;
    }
    // 일정 저장
    public boolean writeSave(String userId, LocalDateTime date, List<Map<String, Object>> plan, String diary) {
//        Member mem = memberRepository.findById(userId).orElseThrow(EmptyStackException::new);
//        WriteDTO writeDTOs = new WriteDTO();

        // plan 일괄 삭제
        planRepository.deleteByUserIdAndPlanDate(userId, date);
        // plan 저장
        int cnt = 0;
        for(Map<String, Object> p : plan) {
            boolean deleted = (boolean) p.get("deleted");
            if(deleted == false) {
                Plan plans = new Plan();
                Member mem = memberRepository.findById(userId).orElseThrow(EmptyStackException::new);
                plans.setUserId(mem);
                plans.setPlanDate(date);
                plans.setPlanChecked(cnt);
                if(p.get("checked").equals(true)) plans.setPlanChecked(1);
                else plans.setPlanChecked(0);
                plans.setPlan((String)p.get("text"));
                Plan rst = planRepository.save(plans);
                log.warn(rst.toString());
                cnt++;
            }
        }
        // diary 업데이트
        List<Diary> diaryList = diaryRepository.findByUserIdAndDiaryDate(userId, date);
        if(diaryList != null) {
            for(Diary d : diaryList) {
                Diary diaries = new Diary();
                Member mem = memberRepository.findById(userId).orElseThrow(EmptyStackException::new);
                diaries.setDiary(diary);
                diaries.setUserId(mem);
                diaries.setDiaryDate(date);
                Diary rst = diaryRepository.save(diaries);
                log.warn(rst.toString());
            }
        } else {
            Diary diaries = new Diary();
            Member mem = memberRepository.findById(userId).orElseThrow(EmptyStackException::new);
            diaries.setUserId(mem);
            diaries.setDiaryDate(date);
            diaries.setDiary(diary);
            Diary rst = diaryRepository.save(diaries);
            log.warn(rst.toString());
        }
        return true;
    }
}
