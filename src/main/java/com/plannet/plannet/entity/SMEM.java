package com.plannet.plannet.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Data
@Entity
@Table(name="s_mem")
public class SMEM {
    @Id
    // 키 값을 생성하는 전략 : 기본키 생성을 JPA 기준
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cal_no", nullable = false)
    private Long calNo; // 각 날짜/아이디의 일정 고유 KEY값 (순서대로 불러 들어올 때 사용)

    @Column(name = "isOwner",length = 1, nullable = false)
    @ColumnDefault("0")
    private int isOwner;

    @Id
    @ManyToOne
    @JoinColumn(name = "id")
    private Member id; // 참가자 아이디
}
