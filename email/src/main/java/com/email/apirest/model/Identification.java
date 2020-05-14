package com.email.apirest.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDate;

@Entity
@Data
public class Identification {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(unique = true)
    private String numberIdentification;

    @ManyToOne
    @JoinColumn(name = "identificationType", referencedColumnName = "code")
    @Valid
    private IdentificationType identificationType;

    @Column
    private LocalDate expeditionDate;

    @Column
    private String expeditionCity;

    @OneToOne(mappedBy = "identification")
    @Getter(AccessLevel.NONE)
    private Customer customer;
}
