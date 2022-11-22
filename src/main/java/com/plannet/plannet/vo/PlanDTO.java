package com.plannet.plannet.vo;

import com.plannet.plannet.entity.Member;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Getter @Setter
public class PlanDTO {
    private Long planNo;
    private Member id;
    private Date planDate;
    private int planChecked;
    private String plan;
}
