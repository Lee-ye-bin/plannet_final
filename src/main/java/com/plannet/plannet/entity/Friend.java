package com.plannet.plannet.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Friend implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "id")
    @Column(length = 15)
    private Member id;
    @Id
    @ManyToOne
    @JoinColumn(name = "friend_id")
    @Column(length = 15)
    private Member friendId;
    @Column(name = "isAccept", length = 1, nullable = false)
    @ColumnDefault("0")
    private int isAccept;
}

