package com.plannet.plannet.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@IdClass(LikeCntPK.class)
public class LikeCnt {
    @Id
    @ManyToOne
    @JoinColumn(name = "id")
    private Member id;
    @Id
    @ManyToOne
    @JoinColumn(name = "board_no") // JoinColumn 의 name 은 조인할 컬럼명을 기입 (SQL 기준 컬럼명)
    private Board boardNo;
}
