package com.plannet.plannet.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int quoteNo;
    @Column(length = 500)
    private String quote;
}
