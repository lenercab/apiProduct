package com.email.apirest.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
     private String address;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String location;

    @Column
    private String street;

    @Column
    private boolean mainAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId", referencedColumnName = "CustomerId")
    @Getter(AccessLevel.NONE)
    private Customer customer;
}

