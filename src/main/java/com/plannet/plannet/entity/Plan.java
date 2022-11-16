package com.plannet.plannet.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
public class Plan {
    @Id
    // 키 값을 생성하는 전략 : 기본키 생성을 JPA 기준
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int planNo;
    @ManyToOne
    @JoinColumn(name="id")
    private Member id;
    private Date planDate;
    private boolean planChecked;
    private String plan;
}