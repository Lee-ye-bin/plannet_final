package com.plannet.plannet.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class BoardDTO {
    private int boardNo;
    private String id;
    private String title;
    private int views;
    private LocalDateTime writeDate;
    private String detail;
    private int isChecked;

    private int likeCnt;
    private int isLiked;

    private String commentWriter;
    private LocalDateTime commentDate;
    private String comment;
}

