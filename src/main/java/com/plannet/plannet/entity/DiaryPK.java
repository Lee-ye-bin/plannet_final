package com.plannet.plannet.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DiaryPK implements Serializable {
    private Member id;
    private LocalDateTime diaryDate;
}