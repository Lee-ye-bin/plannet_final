package com.plannet.plannet.vo;

import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.util.List;
import java.util.Map;


@Getter @Setter
public class WriteDTO {
    private String id;
    private Date date;
    private List<Map<String, Object>> plan;
    private String diary;
}
