package com.plannet.plannet.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class SCAL {
    @Id
    @Column(nullable = false)
    private Long calNo;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id")
    private Member id;

    @Column(length = 40, nullable = false, columnDefinition = "공유 캘린더")
    private String calName;

    @Column(length = 2400)
    private String calMemo;
}
