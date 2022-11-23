package com.plannet.plannet.entity;

import com.plannet.plannet.dao.*;
import com.plannet.plannet.vo.MemberDTO;
import com.plannet.plannet.vo.PlanDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
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
    SMEMRepository smemRepository;
    @Autowired
    SCALRepository scalRepository;
    @Autowired
    SPLANRepository splanRepository;

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
        List<PlanDTO> personalTotal = planRepository.findByUserId(userId);
        List<PlanDTO> personalEnd = planRepository.findByUserIdAndPlanChecked(userId, 1);
        int personalTotalCnt = 0; // 총 일정 갯수
        int personalEndCnt = 0; // 완료된 일정 갯수
        for(PlanDTO e : personalTotal) {
            personalTotalCnt++;
        }
        for(PlanDTO e : personalEnd) {
            personalEndCnt++;
        }
        memberDTO.setPes((int)personalTotalCnt*100/personalEndCnt);
    }
}