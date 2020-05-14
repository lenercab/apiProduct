package com.phone.phone.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
    @JoinColumn(name = "customerId", referencedColumnName = "CustomerId", insertable = false, updatable = false)
    @Getter(AccessLevel.NONE)
    private Customer customer;


    @Column
    private long customerId;
}
