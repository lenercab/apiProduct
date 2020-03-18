package com.address.apirest.model;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;


@Entity
@Data
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String email;

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "CustomerId", insertable = false, updatable = false)
    @Getter(AccessLevel.NONE)
    private Customer customer;

    @Column
    private long customerId;

}
