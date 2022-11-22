package com.plannet.plannet.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;

@Getter @Setter
@ToString
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

    @Column(name = "isOwner", nullable = false)
    @ColumnDefault("0")
    private int isOwner;
}
