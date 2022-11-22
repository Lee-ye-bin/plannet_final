package com.plannet.plannet.entity;

import lombok.Data;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "plan")
public class Plan {
    @Id
    // 키 값을 생성하는 전략 : 기본키 생성을 JPA 기준
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="plan_no", nullable = false)
    private Long planNo; // 각 날짜/아이디의 일정 고유 KEY값 (순서대로 불러 들어올 때 사용)

    @ManyToOne
    @JoinColumn(name="id")
    private Member id; // 회원 아이디

    @Column(name="plan_date", columnDefinition = "DEFAULT SYSDATE")
    @CreatedDate
    private LocalDateTime planDate; // 일정 날짜

// 방법1
//    @Check(constraints = "PLAN_CHECK NUMBER(1) DEFAULT 0 NOT NULL CHECK PLAN_CHECK IN(0,1)")
// 방법2
//    @Column(name="plan_check", length = 1 , nullable = false)
//    @Check(constraints = "plan_check is 0 or plan_check is 1")
    @Column(name="plan_check", length=1, columnDefinition="DEFAULT 0 NOT NULL CHECK PLAN_CHECK IN(0,1)")
    private int planChecked; // 일정 완료여부부

   @Column(name="plan", length = 90, nullable = false)
    private String plan; // 일정
}