package com.plannet.plannet.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter @Setter
@ToString
@Entity
@SequenceGenerator(
        name = "LIKE_GENERATOR",
        sequenceName = "LIKE_SEQUENCES",
        initialValue = 1, allocationSize = 1)
public class LikeCnt {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LIKE_GENERATOR")
    private Long likeCntNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Member userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardNo") // JoinColumn 의 name 은 조인할 컬럼명을 기입 (SQL 기준 컬럼명)
    private Board boardNo;
}
