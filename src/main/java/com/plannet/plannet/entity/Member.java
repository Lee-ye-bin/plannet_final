package com.plannet.plannet.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
public class Member {
    @Id
    private String id;
    private String pwd;
    private String name;
    private String nickname;
    private String email;
    private String tel;
    @CreationTimestamp
    // 자바는 카멜표기법을 따르지만 DB에서는 그렇지 않아서 JOIN_DATE 로 생성됨
    private Date joinDate;
    private String SNS;
    @Column(length = 300)
    private String profile;
    @Column(length = 2400)
    private String memo;
}
