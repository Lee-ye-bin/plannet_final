package com.plannet.plannet.service;

import com.plannet.plannet.dao.*;
import com.plannet.plannet.entity.*;
import com.plannet.plannet.vo.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;


@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentsRepository commentsRepository;
    private final DiaryRepository diaryRepository;
    private final LikeCntRepository likeCntRepository;
    private final FriendRepository friendRepository;
    private final MessageRepository messageRepository;
    private final PlanRepository planRepository;
    private final SCOMRepository scomRepository;
    private final SMEMRepository smemRepository;
    private final SPLANRepository splanRepository;
    private final SCALRepository scalRepository;


    public boolean loginCheck (String id, String pwd){
        try {
            return memberRepository.findById(id).orElseThrow(EntityNotFoundException::new).getPwd().equals(pwd);
        } catch (Exception e) {
            return false;
        }
    }
    public boolean regMember(String id,String pwd,String name,
                             String nickname,String email,String tel){
        try {
            Member member = new Member();
            member.setId(id);
            member.setPwd(pwd);
            member.setName(name);
            member.setNickname(nickname);
            member.setEmail(email);
            member.setTel(tel);
            String userCode = String.format("%04d", (int)(Math.random() * 9999) + 1);
            member.setUserCode(userCode);
            member.setJoinDate(LocalDateTime.now());
            memberRepository.save(member);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean overlapCheck (String uni, String type){
        boolean isNotOverLap = true;
        try{
            Member member;
            char t = type.charAt(5);
            switch (t){
                case 'I' :
                    member = memberRepository.findById(uni).orElseThrow(null);
                    if(member != null) isNotOverLap = false;
                    else break;
                case 'E' :
                    member = memberRepository.findByEmail(uni);
                    if(member != null) isNotOverLap = false;
                    else break;
                case 'T' :
                    member = memberRepository.findByTel(uni);
                    if(member != null) isNotOverLap = false;
                    else break;
            }
            return isNotOverLap;
        } catch (Exception e){
            return isNotOverLap;
        }

    }
    // ????????? ???????????? ??????
    public MemberDTO memberFindCheck(String uni, String email, String type) {
        MemberDTO memDTO = new MemberDTO();
        try{
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
                    memDTO.setOk(true);
                    break;
                case 'P' :
                    mem = memberRepository.findByIdAndEmail(uni, email);
                    if(mem != null) {
                        memDTO.setReg(true);
                    } else {
                        memDTO.setReg(false);
                    }
                    memDTO.setOk(true);
                    break;
            }
        } catch (Exception e) {
            memDTO.setOk(false);
        }
        return memDTO;
    }
    // ???????????? ?????? ??? ??? ???????????? ??????
    public boolean regNewPwd(String id, String pwd) {
        try{
            Member mem = memberRepository.findById(id).orElseThrow(EmptyStackException::new);
            mem.setPwd(pwd);
            Member rst = memberRepository.save(mem);
            log.warn(rst.toString());
        } catch(Exception e) {
            return false;
        }
        return true;
    }
    @Transactional
    public boolean deleteMember(String id){
        try {
            Member member = memberRepository.findById(id).orElseThrow();
            likeCntRepository.deleteByUserId(member);
            commentsRepository.deleteByUserId(member);
            boardRepository.deleteByUserId(member);
            diaryRepository.deleteByUserId(member);
            friendRepository.deleteByUserId(member);
            messageRepository.deleteByUserId(member);
            planRepository.deleteByUserId(member);
            // ???????????? ????????? ?????? ??????
            scomRepository.deleteByUserId(member);
            smemRepository.deleteByUserId(member);
            splanRepository.deleteByUserId(member);
            log.warn("scal ??? ??????");
            scalRepository.deleteByUserId(member);
            memberRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
