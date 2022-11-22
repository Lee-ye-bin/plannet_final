package com.plannet.plannet.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@Entity
@IdClass(DiaryPK.class)
public class Diary {
    @Id
    @ManyToOne
    @JoinColumn(name = "id")
    private Member id;
    @Id
    @CreatedDate
    private LocalDateTime diaryDate;
    @Column(length = 2400, nullable = false)
    private String diary;
}
