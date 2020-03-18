package com.customer.apirest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;
import java.util.List;

import static javax.persistence.CascadeType.*;


@ApiModel(description = "This model class represents the data basic one customer")
@Entity
@Data
public class Customer  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CustomerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "identificationId", referencedColumnName = "id")
    private Identification identification;

    @Column
    private String name;


    @Column
    private String lastName;

    @Column
    private String country;

    @Column
    private String birthCity;


    @Column
    private LocalDate birthDate;

    @Column
    @NotNull(message = "the maritalStatus field is required")
    private String maritalStatus;

    @ManyToOne
    @JoinColumn(name = "genderId", referencedColumnName = "code")
    private Gender gender;

    @ApiModelProperty(example = "system engineer")
    @Column
    private String profession;

    @OneToMany(mappedBy = "customer")
    private List<Email> emails;

    @OneToMany(mappedBy = "customer")
    private List<Phone> phones;

    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;

}