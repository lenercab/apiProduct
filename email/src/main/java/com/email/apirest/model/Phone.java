package com.email.apirest.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Data
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String numberPhone;

    @Column
    private String typePhone;

    @Column
    private String operator;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId", referencedColumnName = "CustomerId")
    @Getter(AccessLevel.NONE)
    private Customer customer;
}
