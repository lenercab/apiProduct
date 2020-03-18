package com.email.apirest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CustomerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "identificationId", unique = true, referencedColumnName = "id")
    @Valid
    private Identification identification;

    @Column
    private String name;

    @Column
    @NotNull(message = "the field lastName is required")
    @NotEmpty(message = "lastName: empty field is not accepted")
    private String lastName;

    @Column
    private String country;

    @Column
    private String birthCity;

    @Column
    @NotNull(message = "the field birthDate is required")
    private LocalDate birthDate;

    @Column
    @NotNull(message = "the maritalStatus field is required")
    private String maritalStatus;

    @ManyToOne
    @JoinColumn(name = "genderId", referencedColumnName = "code")
    @Valid
    private Gender gender;

    @Column
    private String profession;

    @OneToMany(mappedBy = "customer")
    private List<Email> emails;

    @OneToMany(mappedBy = "customer")
    private List<Phone> phones;

    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;

}