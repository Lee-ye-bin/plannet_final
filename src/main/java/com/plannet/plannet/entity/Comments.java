package com.plannet.plannet.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@ToString
@Entity
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentNo;

    @ManyToOne
    @JoinColumn(nullable = false, name = "board_no")
    private Board boardNo;

    @ManyToOne
    @JoinColumn(name = "id")
    private Member id;

    @CreatedDate
    private LocalDateTime writeDate;

    @Column(nullable = false, length = 500)
    private String detail;
}



