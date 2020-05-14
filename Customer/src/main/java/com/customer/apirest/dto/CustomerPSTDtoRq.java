package com.customer.apirest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@ApiModel(description = "This model class represents the data basic one customer")
@Data
public class CustomerPSTDtoRq  {

    @ApiModelProperty(example = "1")
    private long customerId;

    @Valid
    private IdentificationDto identification;

    @NotNull(message = "the field name is required")
    @NotEmpty(message = "name: empty field is not accepted")
    @ApiModelProperty(notes = "it is necessary to enter name customer", example = "Leehener", required = true)
    private String name;

    @NotNull(message = "the field lastName is required")
    @NotEmpty(message = "lastName: empty field is not accepted")
    @ApiModelProperty(notes = "it is necessary to enter lastname customer", example = "Cabeza", required = true)
    private String lastName;

    @ApiModelProperty(example = "Colombia")
    private String country;

    @ApiModelProperty(example = "Cartagena")
    private String birthCity;

    @ApiModelProperty(example = "1982-04-24", required = true)
    @NotNull(message = "the field birthDate is required")
    private LocalDate birthDate;

    @ApiModelProperty(example = "Soltero", required = true)
    @NotNull(message = "the maritalStatus field is required")
    private String maritalStatus;

    @Valid
    private GenderDto gender;

    @ApiModelProperty(example = "system engineer")
    private String profession;

}
