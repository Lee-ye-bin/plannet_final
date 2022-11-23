package com.plannet.plannet.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@ToString
@Entity
public class Board {
    @Id
    // 키 값을 생성하는 전략 : 기본키 생성을 JPA 기준
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int boardNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Member userId;

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

    @Column(name = "ischecked", nullable = false)
    @ColumnDefault("0")
    private int isChecked;
}
