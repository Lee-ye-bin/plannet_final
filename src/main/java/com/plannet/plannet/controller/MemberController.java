package com.plannet.plannet.controller;

import com.plannet.plannet.service.MemberService;
import com.plannet.plannet.vo.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
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
            return new ResponseEntity(false, HttpStatus.OK);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerMember(@RequestBody Map<String, String> regData) {
        try{
            String id = regData.get("id");
            String pwd = regData.get("pwd");
            String name = regData.get("name");
            String email = regData.get("mail");
            String nickname = regData.get("nickname");
            String tel =regData.get("tel");

            boolean result = memberService.regMember(id, pwd, name, email, nickname, tel);
            if(result){
                return new ResponseEntity(true, HttpStatus.OK);
            }
            else {
                return new ResponseEntity(false, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity(false, HttpStatus.OK);
        }

    }

    @PostMapping("/overlap_check")
    public ResponseEntity<Boolean> overlapCheck(@RequestBody Map<String, String> checkData){
        try{
            String uni = checkData.get("uni");
            String type = checkData.get("type");

            boolean result = memberService.overlapCheck(uni, type);
            if(result){
                return new ResponseEntity(true, HttpStatus.OK);
            }
            else {
                return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return  new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
    }
    // 아이디 비밀번호 찾기
    @PostMapping("/find_check")
    public ResponseEntity<List<MemberDTO>> memberFind(@RequestBody Map<String, String> memFind) {
        String uni = memFind.get("uni");
        String email = memFind.get("email");
        String type = memFind.get("type");

        MemberDTO memberDTO = memberService.memberFindCheck(uni, email, type);
        if(memberDTO.isOk()) return new ResponseEntity(memberDTO.getId(), HttpStatus.OK);
        else return new ResponseEntity(false, HttpStatus.OK);
    }
    // 비밀번호 찾기 시 새 비밀번호 설정
    @PostMapping("/new_pwd")
    public ResponseEntity<Boolean> memberNewPwd(@RequestBody Map<String, String> newPwd) {
        String id = newPwd.get("id");
        String pwd = newPwd.get("pwd");

        boolean result = memberService.regNewPwd(id, pwd);
        if(result) {
            return new ResponseEntity(true, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(false, HttpStatus.OK);
        }
    }
    @PostMapping("/member_delete")
    public ResponseEntity<Boolean> memberDelete(@RequestBody Map<String,String> delete){
        String id = delete.get("id");
        boolean member = memberService.deleteMember(id);
        if(member){
            return new ResponseEntity(true,HttpStatus.OK);
        }
        else{
            return new ResponseEntity(false,HttpStatus.OK);
        }
    }
}