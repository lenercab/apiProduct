package com.phone.phone.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
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
    private IdentificationType identificationType;


    @Column
    private LocalDate expeditionDate;

    @Column
    private String expeditionCity;

    @OneToOne(mappedBy = "identification")
    @Getter(AccessLevel.NONE)
    private Customer customer;
}
