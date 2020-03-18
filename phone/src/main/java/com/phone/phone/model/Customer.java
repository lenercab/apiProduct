package com.phone.phone.model;

import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Data
public class Customer extends ResourceSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private LocalDate birthDate;

    @Column
    private String maritalStatus;

    @ManyToOne
    @JoinColumn(name = "genderId", referencedColumnName = "code")
    private Gender gender;

    @Column
    private String profession;

    @OneToMany(mappedBy = "customer")
    private List<Email> emails;

    @OneToMany(mappedBy = "customer", orphanRemoval = true)
    private List<Phone> phones;

    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;

}