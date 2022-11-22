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
@Table(name = "s_plan")
public class SPLAN {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long planNo;

    @ManyToOne
    @JoinColumn(name = "cal_no")
    private SCAL calNo;

    @CreatedDate
    private LocalDateTime planDate;

    @ManyToOne
    @JoinColumn(name = "id")
    private Member id;

    @Column(nullable = false, name = "plan_check")
    @ColumnDefault("0")
    private int planCheck;

    @Column(nullable = false, length = 90)
    private String plan;
}
