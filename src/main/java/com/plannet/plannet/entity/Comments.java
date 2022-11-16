package com.plannet.plannet.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
public class Comments {
    @Id
    // 키 값을 생성하는 전략 : 기본키 생성을 JPA 기준
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commentNo;
    @ManyToOne
    @JoinColumn(name="boardNo")
    private Board boardNo;
    @ManyToOne
    @JoinColumn(name="id")
    private Member id;
    @CreationTimestamp
    private Date writeDate;
    @Column(length = 500)
    private String detail;
}
