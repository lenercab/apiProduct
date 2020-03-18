package com.phone.phone.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel(description = "This model class represents the data basic one phone")
@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull(message = "the field address is required")
    @NotEmpty(message = "address: empty field is not accepted")
    @ApiModelProperty(notes = "it is necessary to enter address", required = true)
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

