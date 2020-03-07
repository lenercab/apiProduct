package com.email.apirest.model;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel(description = "This model class represents the data basic one phone")
@Entity
@Data
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "the field numberPhone is required")
    @NotEmpty(message = "numberPhone: empty field is not accepted")
    @Pattern(regexp = "^\\d+$", message = "the numberPhone field should be numeric")
    @Column
    private String numberPhone;

    @NotNull(message = "the field numberPhone is required")
    @NotEmpty(message = "numberPhone: empty field is not accepted")
    @Column
    private String typePhone;
    @Column
    private String operator;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId", referencedColumnName = "CustomerId")
    @Getter(AccessLevel.NONE)
    private Customer customer;
}
