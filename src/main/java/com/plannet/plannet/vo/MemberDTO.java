package com.plannet.plannet.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class MemberDTO {
    private String id;
    private String userCode;
    private String pwd;
    private String name;
    private String nickname;
    private String email;
    private String tel;
    private String SNS;
    private String profile;
    private String memo;
    private String proImg;
    private List<ShareDTO> sCalList; // 공유 캘린더의 정보가 넘어옴, 번호, 이름, 달성률
}
