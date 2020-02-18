package com.customer.apirest.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Identification {


    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(unique = true)
    private String numberIdentification;

    @ManyToOne
    @JoinColumn(name = "identificationType", referencedColumnName = "code")
    private IdentificationType identificationType;

    @Column
    private Date expeditionDate;

    @Column
    private String expeditionCity;

    @OneToOne(mappedBy = "identification")
    @Getter(AccessLevel.NONE)
    private Customer customer;
}
