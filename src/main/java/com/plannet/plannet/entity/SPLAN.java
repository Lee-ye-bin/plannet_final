package com.plannet.plannet.entity;

import lombok.Data;
<<<<<<< HEAD
import org.hibernate.annotations.ColumnDefault;
=======
>>>>>>> origin/master
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
<<<<<<< HEAD
@Table(name = "s_plan")
public class SPLAN {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long planNo;
    @ManyToOne
    @JoinColumn(name = "cal_no")
    private SCAL calNo;
=======
public class SPLAN implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "cal_no")
    private SCAL calNo;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long planNo;
>>>>>>> origin/master
    @CreatedDate
    private LocalDateTime planDate;
    @ManyToOne
    @JoinColumn(name = "id")
    private Member id;
<<<<<<< HEAD
    @Column(nullable = false, name = "plan_check")
    @ColumnDefault("0")
=======
    @Column(nullable = false, name = "plan_check", length = 1, columnDefinition = "DEFAULT 0 NOT NULL CHECK PLAN_CHECK IN(0,1)")
>>>>>>> origin/master
    private int planCheck;
    @Column(nullable = false, length = 90)
    private String plan;
}
