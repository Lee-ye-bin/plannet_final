package com.plannet.plannet.vo;

import com.plannet.plannet.entity.Comments;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter @Setter
public class BoardDTO {
    private int boardNo;
    private String id;
    private String title;
    private int views;
    private Date writeDate;
    private String detail;
    private int isChecked;
    private int likeCnt;
    private boolean isLiked;
    private List<Comments> comments;
}