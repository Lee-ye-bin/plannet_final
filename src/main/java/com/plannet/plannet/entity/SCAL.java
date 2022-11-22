package com.plannet.plannet.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter @Setter
@ToString
@Entity
@Table(name = "s_cal")
public class SCAL {
    @Id
    @Column(nullable = false)
    private Long calNo;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id")
    private Member id;

    @Column(length = 40, nullable = false)
    private String calName;

    @Column(length = 2400)
    private String calMemo;
}
