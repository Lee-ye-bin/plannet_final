package com.plannet.plannet.service;

import com.plannet.plannet.dao.MemberRepository;
import com.plannet.plannet.entity.Member;
import com.plannet.plannet.vo.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class MemberService {
    private MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public boolean loginCheck (String id, String pwd){
        List<Member> memberList = memberRepository.findByIdAndPwd(id,pwd);
        for(Member e : memberList){
            return true;
        }
        return false;
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


}