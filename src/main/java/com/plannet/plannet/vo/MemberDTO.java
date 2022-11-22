package com.plannet.plannet.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
    private String id;
    private int userCode;
    private String pwd;
    private String name;
    private String nickName;
    private String email;
    private String tel;
    private String joinDate;
    private String sns;
    private String profile;
    private String memo;
    private String proImg;
}
