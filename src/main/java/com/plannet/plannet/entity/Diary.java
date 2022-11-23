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
@IdClass(DiaryPK.class)
public class Diary {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Member id;

    @Id
    @CreatedDate
    private LocalDateTime diaryDate;

    @Column(length = 2400, nullable = false)
    private String diary;
}
