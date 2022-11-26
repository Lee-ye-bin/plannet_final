package com.plannet.plannet.service;

import com.plannet.plannet.dao.*;
import com.plannet.plannet.entity.*;
import com.plannet.plannet.vo.MemberDTO;
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

        if(a != null ) isNotReg=false;
        else isNotReg=true;
        return isNotReg;
    }
    // 아이디 비밀번호 찾기
    public MemberDTO memberFindCheck(String uni, String email, String type) {
        MemberDTO memDTO = new MemberDTO();
        char t = type.charAt(5);
        Member mem = new Member();

        switch (t) {
            case 'I' :
                mem = memberRepository.findByNameAndEmail(uni, email);
                if(mem != null) {
                    memDTO.setReg(true);
                    memDTO.setId(mem.getId());
                } else {
                    memDTO.setReg(false);
                }
                break;
            case 'P' :
                mem = memberRepository.findByIdAndEmail(uni, email);
                if(mem != null) {
                    memDTO.setReg(true);
                } else {
                    memDTO.setReg(false);
                }
                break;
        }
        return memDTO;
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
