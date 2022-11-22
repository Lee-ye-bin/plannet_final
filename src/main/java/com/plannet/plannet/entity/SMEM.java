package com.plannet.plannet.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "s_mem")
@IdClass(SMEMPK.class)
public class SMEM implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "cal_no")
    private SCAL calNo;

    @Id
    @ManyToOne
    @JoinColumn(name = "id")
    private Member id; // 참가자 아이디

    @Column(name = "isOwner",length = 1, nullable = false)
    @ColumnDefault("0")
    private int isOwner;
}
