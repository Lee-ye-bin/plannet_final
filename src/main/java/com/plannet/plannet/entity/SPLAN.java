package com.plannet.plannet.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
public class SPLAN implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "cal_no")
    private SCAL calNo;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long planNo;
    @CreatedDate
    private LocalDateTime planDate;
    @ManyToOne
    @JoinColumn(name = "id")
    private Member id;
    @Column(nullable = false, name = "plan_check", length = 1, columnDefinition = "DEFAULT 0 NOT NULL CHECK PLAN_CHECK IN(0,1)")
    private int planCheck;
    @Column(nullable = false, length = 90)
    private String plan;
}
