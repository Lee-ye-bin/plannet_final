package com.plannet.plannet.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
public class Diary implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name="id")
    private Member id;
    @Id
    private Date diaryDate;
    @Column(length = 2400)
    private String diary;
}

