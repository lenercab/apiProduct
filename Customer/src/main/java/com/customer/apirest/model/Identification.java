package com.customer.apirest.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;

import java.time.LocalDate;

import static javax.persistence.CascadeType.ALL;


@Entity
@Data
public class Identification {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String numberIdentification;

    @ManyToOne
    @JoinColumn(name = "identificationType", referencedColumnName = "code")
   private IdentificationType identificationType;


    @Column
    private LocalDate expeditionDate;

    @Column
    private String expeditionCity;

    @OneToOne(mappedBy = "identification")
//    @Getter(AccessLevel.NONE)
    private Customer customer;
}
