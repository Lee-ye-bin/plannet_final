package com.plannet.plannet.entity;

import lombok.Data;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@IdClass(FriendPK.class)
public class Friend implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "id")
    private Member id;
    @Id
    @ManyToOne
    @JoinColumn(name = "friend_id")
    @Column(length = 15)
    private Member friendId;
    @Column(name = "isAccept", length = 1, nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "isAccept = 0 or isAccept = 1")
    private int isAccept;
}

