package com.plannet.plannet.service;

import com.plannet.plannet.dao.*;
import com.plannet.plannet.entity.*;
import com.plannet.plannet.vo.MemberDTO;
import com.plannet.plannet.vo.ShareDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.*;


@Service
@Slf4j
public class MemberService {
    private MemberRepository memberRepository;
    private PlanRepository planRepository;
    private SMEMRepository smemRepository;
    private SCALRepository scalRepository;
    private SPLANRepository splanRepository;
    public MemberService(MemberRepository memberRepository, PlanRepository planRepository,
                         SMEMRepository smemRepository, SCALRepository scalRepository,
                         SPLANRepository splanRepository){
        this.memberRepository = memberRepository;
        this.planRepository = planRepository;
        this.smemRepository = smemRepository;
        this.scalRepository = scalRepository;
        this.splanRepository = splanRepository;
    }

    public boolean loginCheck (String id, String pwd){
        try{
            memberRepository.findByIdAndPwd(id,pwd);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean regMember(String id,String pwd,String name,
                             String nickname,String mail,String tel){
        Member member = new Member();
        member.setId(id);
        member.setPwd(pwd);
        member.setName(name);
        member.setNickname(nickname);
        member.setEmail(mail);
        member.setTel(tel);
        member.setJoinDate(LocalDateTime.now());
        Member rst = memberRepository.save(member);
        log.warn(rst.toString());
        return true;
    }
    public boolean overlapCheck (String uni, String type){
        boolean isNotReg = false;
        String a = null;
        char t = type.charAt(5);
        switch (t){
            case 'I' :
                a= String.valueOf(memberRepository.findById(uni));
                break;
            case 'E' :
                a= String.valueOf(memberRepository.findByEmail(uni));
                break;
            case 'T' :
                a= String.valueOf(memberRepository.findByTel(uni));
                break;
        }

        if(!Objects.requireNonNull(a).isBlank()) isNotReg=true;
        return isNotReg;
    }

    // 비밀번호 찾기 시 새 비밀번호 설정
    public boolean regNewPwd(String id, String pwd) {
        Member mem = memberRepository.findById(id).orElseThrow(EmptyStackException::new);
        mem.setPwd(pwd);
        Member rst = memberRepository.save(mem);
        log.warn(rst.toString());
        return true;
    }

    public MemberDTO userInfo (String userId) { // 사용자 정보 불러오기
        MemberDTO memberDTO = new MemberDTO();
        Member member = memberRepository.findById(userId).orElseThrow(EntityNotFoundException::new);

        // 멤버 인포
        memberDTO.setNickname(member.getNickname());
        memberDTO.setId(member.getId());
        memberDTO.setUserCode(member.getUserCode());
        memberDTO.setProfile(member.getProfile());
        memberDTO.setEmail(member.getEmail());
        memberDTO.setSns(member.getSNS());
        memberDTO.setTel(member.getTel());
        memberDTO.setProImg(member.getProImg());

        return memberDTO;
    }

    //개인 일정 달성률/공유캘린더정보 불러오기
    public MemberDTO navInfo (String userId) {
        MemberDTO memberDTO = new MemberDTO();
        Member member = memberRepository.findById(userId).orElseThrow(EntityNotFoundException::new);

        //멤버달성률
        List<Plan> personalTotal = planRepository.findByUserId(member);
        List<Plan> personalEnd = planRepository.findByUserIdAndPlanChecked(member, 1);
        int personalTotalCnt = 0; // 총 일정 갯수
        int personalEndCnt = 0; // 완료된 일정 갯수
        for(Plan e : personalTotal) {
            personalTotalCnt++;
        }
        for(Plan e : personalEnd) {
            personalEndCnt++;
        }
        int personalPes = 0;
        try {
            personalPes = personalEndCnt * 100 / personalTotalCnt;
        } catch (ArithmeticException ignored) {}
        log.warn(String.valueOf(personalPes));
        memberDTO.setPes(personalPes);


        // 공유캘린더정보 불러오기
        List<SMEM> smemList = smemRepository.findByUserId(member);
        List<List<Object>> sCalList = new ArrayList<>();
        for(SMEM e : smemList){
            List<Object> sCal = new ArrayList<>();
            Long calNo = e.getCalNo().getCalNo(); // 캘린더 넘버
            String calName = e.getCalNo().getCalName(); // 캘린더 이름

            // 공유캘린더 일정 달성률 구하기
            SCAL scal = scalRepository.findById(calNo).orElseThrow(EntityNotFoundException::new);
            List<SPLAN> sPlanTotal = splanRepository.findByCalNo(scal);
            List<SPLAN> sPlanEnd = splanRepository.findByCalNoAndPlanChecked(scal, 1);
            int sPlanTotalCnt = 0; // 총 일정 갯수
            int sPlanEndCnt = 0; // 완료된 일정 갯수
            for(SPLAN f : sPlanTotal) {
                sPlanTotalCnt++;
            }
            for(SPLAN f : sPlanEnd) {
                sPlanEndCnt++;
            }

            int calPes = 0;
            try {
                calPes = sPlanEndCnt * 100 / sPlanTotalCnt;
            } catch (ArithmeticException ignored) {}

            sCal.add(calNo);
            sCal.add(calName);
            sCal.add(calPes);
            sCalList.add(sCal);
        }

        memberDTO.setSCalList(sCalList);

        return memberDTO;
    }
}
