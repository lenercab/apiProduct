package com.phone.phone.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity
@Data
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ApiModelProperty(notes = "it is necessary to enter number phone", example = "3135718791", required = true)
    @NotNull(message = "the field numberPhone is required")
    @NotEmpty(message = "numberPhone: empty field is not accepted")
    @Pattern(regexp = "^\\d+$", message = "the numberPhone field should be numeric")
    @Column
    private String numberPhone;

    @ApiModelProperty(example = "Movil")
    @NotNull(message = "the field numberPhone is required")
    @NotEmpty(message = "numberPhone: empty field is not accepted")
    @Column
    private String typePhone;

    @ApiModelProperty(example = "Tigo")
    @Column
    private String operator;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId", referencedColumnName = "CustomerId", insertable = false, updatable = false)
    @Getter(AccessLevel.NONE)
    private Customer customer;


    @ApiModelProperty(notes = "it is necessary to enter customer id", example = "1", required = true)
    @Column
    @Min(value = 1, message = "the minimum duty value 1 or customerId should to be a value valid")
    private long customerId;
}
