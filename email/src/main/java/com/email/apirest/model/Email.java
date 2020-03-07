package com.email.apirest.model;


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

@ApiModel(description = "This model class represents the data basic one email")
@Entity
@Data
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @ApiModelProperty(notes = "it is necessary to enter email", example = "example@example.com", required = true)
    @NotNull(message = "the field email is required")
    @NotEmpty(message = "email: empty field is not accepted")
    @Pattern(regexp = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$", message = "it is should format email correct for example: xxxxx@xxx.com")
    private String email;

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "CustomerId", insertable = false, updatable = false)
    @Getter(AccessLevel.NONE)
    private Customer customer;

    @ApiModelProperty(notes = "it is necessary to enter customer id", example = "1", required = true)
    @Column
    @Min(value = 1, message = "the minimum duty value 1 or customerId should to be a value valid")
    private long customerId;

}
