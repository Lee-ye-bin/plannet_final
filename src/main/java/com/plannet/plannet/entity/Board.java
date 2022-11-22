package com.plannet.plannet.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@Entity
public class Board {
    @Id
    // 키 값을 생성하는 전략 : 기본키 생성을 JPA 기준
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int boardNo;
    @Column(length = 15, nullable = false)
    @ManyToOne
    @JoinColumn(name = "id")
    private Member id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(nullable = false)
    @ColumnDefault("0")
    private int views;
    @CreatedDate
    private LocalDateTime writeDate;
    @Lob
    @Column(nullable = false)
    private String detail;
    @Column(name = "isChecked", length = 1)
    @ColumnDefault("0")
    private int isChecked;
}
