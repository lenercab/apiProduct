package com.customer.apirest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Pattern;

import java.util.Set;

@ApiModel(description = "This model class represents the gender of the customer")
@Entity
@Data
public class Gender {

    @ApiModelProperty(notes = "The unique values accepted were M or F", example = "M", required = true, position = 1)
    @Id
    @Column(unique = true)
    @Pattern(regexp = "^M|F$", message = "the field code of gender should be M or F")
    private String code;

    @Column(unique = true)
    private String description;

    @OneToMany(mappedBy = "gender")
    @Getter(AccessLevel.NONE)
    private Set<Customer> customers;

}