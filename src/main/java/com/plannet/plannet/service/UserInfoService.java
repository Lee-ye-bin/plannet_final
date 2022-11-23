package com.plannet.plannet.service;

import com.plannet.plannet.dao.MemberRepository;
import com.plannet.plannet.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;

@Service
@Slf4j
public class UserInfoService {
    private MemberRepository memberRepository;

    public UserInfoService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // 사용자 정보 수정
    public boolean saveUserInfo(String id, String nickname, String email, String phone, String sns, String profile) {
        Member mem = memberRepository.findById(id).orElseThrow(EmptyStackException::new);
        mem.setNickname(nickname);
        mem.setEmail(email);
        mem.setTel(phone);
        mem.setSNS(sns);
        mem.setProfile(profile);
        Member rst = memberRepository.save(mem);
        log.warn(rst.toString());
        return true;
    }
    // 사용자 프로필 이미지명 저장
    public boolean saveUserImg(String id, String imgName) {
        Member mem = memberRepository.findById(id).orElseThrow(EmptyStackException::new);
        mem.setProImg(imgName);
        Member rst = memberRepository.save(mem);
        log.warn(rst.toString());
        return true;
    }
}
