package com.plannet.plannet.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
public class Board {
    @Id
    // 키 값을 생성하는 전략 : 기본키 생성을 JPA 기준
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int boardNo;
    @ManyToOne
    @JoinColumn(name="id")
    private Member id;
    private String title;
    private int views;
    @CreationTimestamp
    private Date writeDate;
    @Lob
    private String detail;
    private boolean isChecked;
}
