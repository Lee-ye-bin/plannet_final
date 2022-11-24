package com.plannet.plannet.controller;

import com.plannet.plannet.service.MemberService;
import com.plannet.plannet.vo.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
public class MemberController {
    private MemberService memberService;
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> memberLogin(@RequestBody Map<String, String> loginData){
        String id = loginData.get("id");
        String pwd = loginData.get("pwd");
        boolean result = memberService.loginCheck(id,pwd);
        if(result){
            return new ResponseEntity(true, HttpStatus.OK);
        }
        else{
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register_member")
    public ResponseEntity<Boolean> registerMember(@RequestBody Map<String,String> regData) {
        String id = regData.get("id");
        String pwd = regData.get("pwd");
        String name = regData.get("name");
        String email = regData.get("mail");
        String nickname = regData.get("nickname");
        String tel =regData.get("tel");

        boolean result = memberService.regMember(id,pwd,name,email,nickname,tel);
        if(result){
            return new ResponseEntity(true, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/overlap_check")
    public ResponseEntity<Boolean> overlapCheck (@RequestBody Map<String,String> checkData){
        String uni = checkData.get("uni");
        String type = checkData.get("type");

        boolean result = memberService.overlapCheck(uni,type);
        if(result){
            return new ResponseEntity(true, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
    }

    // 비밀번호 찾기 시 새 비밀번호 설정
    @PutMapping("/member_newPwd")
    public ResponseEntity<Boolean> memberNewPwd(@RequestBody Map<String, String> newPwd) {
        String id = newPwd.get("id");
        String pwd = newPwd.get("pwd");

        boolean result = memberService.regNewPwd(id, pwd);
        if(result) {
            return new ResponseEntity(true, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/nav_info")
    public ResponseEntity<Map<String, Object>> NavInfo(@RequestBody Map<String, String> userId) {
        String id = userId.get("id");
        MemberDTO memberDTO1 = memberService.userInfo(id);
        MemberDTO memberDTO2 = memberService.navInfo(id);
        Map<String, Object> navList = new HashMap<>();
        List<Object> userInfo = new ArrayList<>();
        userInfo.add(memberDTO1.getId());
        userInfo.add(memberDTO1.getUserCode());
        userInfo.add(memberDTO1.getNickname());
        userInfo.add(memberDTO1.getEmail());
        userInfo.add(memberDTO1.getTel());
        userInfo.add(memberDTO2.getPes());

        navList.put("userInfo", userInfo);
        navList.put("scalInfo", memberDTO2.getSCalList());

        return new ResponseEntity(navList, HttpStatus.OK);
    }
}