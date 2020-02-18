package com.customer.apirest.model;

import lombok.Data;

import javax.persistence.*;

import org.springframework.hateoas.ResourceSupport;

import java.util.Date;

@Entity
@Data
public class Customer extends ResourceSupport{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
   
    private Long CustomerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "identificationId", unique = true, referencedColumnName = "id")
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
    private Date birthDate;
    @Column
    private String maritalStatus;
    @ManyToOne
    @JoinColumn(name = "genderId", referencedColumnName = "code")
    private Gender gender;
    @Column
    private String profession;

}
