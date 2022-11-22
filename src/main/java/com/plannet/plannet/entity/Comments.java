package com.plannet.plannet.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@Entity
public class Comments {
    @Id
    @Column
    private int commentNo;
    @Column(nullable = false)
    private int boardNo;
    @Column(nullable = false, length = 15)
    private String id;
    @CreatedDate
    private LocalDateTime writeDate;
    @Column(nullable = false, length = 500)
    private String detail;
}



