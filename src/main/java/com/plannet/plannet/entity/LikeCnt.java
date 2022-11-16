package com.plannet.plannet.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class LikeCnt implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name="boardNo")
    private Board boardNo;
    @Id
    @ManyToOne
    @JoinColumn(name="id")
    private Member id;
}
