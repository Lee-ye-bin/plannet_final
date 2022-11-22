package com.plannet.plannet.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class SCOM {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentNo;
    @ManyToOne
    @JoinColumn(name = "cal_no")
    private Long calNo;
    @CreatedDate
    private LocalDateTime planDate;
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Member id;
    @CreatedDate
    private LocalDateTime writeDate;
    @Column(nullable = false, length = 500)
    private String detail;
}
