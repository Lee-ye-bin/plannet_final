package com.plannet.plannet.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class Diary implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "id")
    @Column(length = 15)
    private Member id;
    @Id
    @CreatedDate
    private LocalDateTime diaryDate;
    @Column(length = 2400, nullable = false)
    private String diary;
}

