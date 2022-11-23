package com.plannet.plannet.controller;

import com.plannet.plannet.service.MemberService;
import com.plannet.plannet.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
public class UserInfoController {
    private UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    // 사용자 정보 수정
    @PutMapping("/UserInfoSave")
    public ResponseEntity<Boolean> userInfoSave(@RequestBody Map<String, String> userInfo) {
        String id = userInfo.get("id");
        String nickname = userInfo.get("nickname");
        String email = userInfo.get("email");
        String phone = userInfo.get("phone");
        String sns = userInfo.get("sns");
        String profile = userInfo.get("profile");

        boolean result = userInfoService.saveUserInfo(id, nickname, email, phone, sns, profile);
        if(result) {
            return new ResponseEntity(true, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
    }
}
