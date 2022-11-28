package com.plannet.plannet.service;

import com.plannet.plannet.dao.*;
import com.plannet.plannet.entity.*;
import com.plannet.plannet.vo.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.*;


@Service
@Slf4j
public class MemberService {
    private MemberRepository memberRepository;
    private BoardRepository boardRepository;
    private CommentsRepository commentsRepository;
    private DiaryRepository diaryRepository;
    private LikeCntRepository likeCntRepository;
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public boolean loginCheck (String id, String pwd){
        try {
            return memberRepository.findById(id).orElseThrow(EntityNotFoundException::new).getPwd().equals(pwd);
        } catch (Exception e) {
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
        Member member = new Member();
        MemberDTO memberDTO = new MemberDTO();
        char t = type.charAt(5);
        switch (t){
            case 'I' :
                member = memberRepository.findById(uni).orElseThrow(null);
                if(member != null) memberDTO.setNotOverlap(false);
                else memberDTO.setNotOverlap(true);
                break;
            case 'E' :
                member = memberRepository.findByEmail(uni);
                if(member != null) memberDTO.setNotOverlap(false);
                else memberDTO.setNotOverlap(true);
                break;
            case 'T' :
                member = memberRepository.findByTel(uni);
                if(member != null) memberDTO.setNotOverlap(false);
                else memberDTO.setNotOverlap(true);
                break;
        }
        return memberDTO.isNotOverlap();
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
    public boolean deleteMember(String id){
        try {

            return true;
        }catch (Exception e){
            return false;
        }
    }
}
