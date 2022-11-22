package com.plannet.plannet.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter @Setter
@ToString
@Entity
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int quoteNo;
    
    @Column(length = 500, nullable = false)
    private String quote;
}
