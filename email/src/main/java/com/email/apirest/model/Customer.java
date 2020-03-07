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


@ApiModel(description = "This model class represents the data basic one customer")
@Entity
@Data
public class Customer extends ResourceSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CustomerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "identificationId", unique = true, referencedColumnName = "id")
    @Valid
    private Identification identification;

    @ApiModelProperty(notes = "it is necessary to enter name customer", example = "Leehener", required = true)
    @Column
    @NotNull(message = "the field name is required")
    @NotEmpty(message = "name: empty field is not accepted")
    private String name;

    @ApiModelProperty(notes = "it is necessary to enter lastname customer", example = "Cabeza", required = true)
    @Column
    @NotNull(message = "the field lastName is required")
    @NotEmpty(message = "lastName: empty field is not accepted")
    private String lastName;

    @ApiModelProperty(example = "Colombia")
    @Column
    private String country;

    @ApiModelProperty(example = "Cartagena")
    @Column
    private String birthCity;

    @ApiModelProperty(example = "1982-04-24", required = true)
    @Column
    @NotNull(message = "the field birthDate is required")
    private LocalDate birthDate;

    @ApiModelProperty(example = "Soltero", required = true)
    @Column
    @NotNull(message = "the maritalStatus field is required")
    private String maritalStatus;

    @ManyToOne
    @JoinColumn(name = "genderId", referencedColumnName = "code")
    @Valid
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