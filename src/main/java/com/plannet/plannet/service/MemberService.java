package com.plannet.plannet.service;

import com.plannet.plannet.dao.*;
import com.plannet.plannet.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
@Slf4j
public class MemberService {
    private MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
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
}
