package com.plannet.plannet.entity;

import lombok.Data;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@IdClass(FriendPK.class)
public class Friend {
    @Id
    @ManyToOne
    @JoinColumn(name = "id")
    private Member id;
    @Id
    @ManyToOne
    @JoinColumn(name = "friend_id")
    private Member friendId;
    @Column(name = "isaccept", nullable = false)
    @ColumnDefault("0")
    private int isAccept;
}

