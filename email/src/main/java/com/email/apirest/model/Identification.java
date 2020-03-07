package com.email.apirest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@ApiModel(description = "This model class represents the information basic of the identification customer")
@Entity
@Data
public class Identification {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @ApiModelProperty(notes = "it is necessary to enter an identification number", example = "9297638", required = true)
    @Column(unique = true)
    @Pattern(regexp = "^\\d+$", message = "the numberIdentification field should be numeric")
    private String numberIdentification;

    @ManyToOne
    @JoinColumn(name = "identificationType", referencedColumnName = "code")
    @Valid
    private IdentificationType identificationType;

    @ApiModelProperty(notes = "this is date expedition of the document", example = "2000-11-20")
    @Column
    private LocalDate expeditionDate;
    @ApiModelProperty(notes = "document issuing city", example = "Cartagena")
    @Column
    private String expeditionCity;

    @OneToOne(mappedBy = "identification")
    @Getter(AccessLevel.NONE)
    private Customer customer;
}
