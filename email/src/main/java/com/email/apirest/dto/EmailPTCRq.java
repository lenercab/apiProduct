package com.email.apirest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel(description = "This model class represents the data basic one email")
@Data
public class EmailPTCRq {

    @ApiModelProperty(example = "1", required = true)
    private long id;


    @ApiModelProperty(notes = "it is necessary to enter email", example = "leehcab@hotmail.com", required = true)
    @NotNull(message = "the field email is required")
    @NotEmpty(message = "email: empty field is not accepted")
    @Pattern(regexp = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$", message = "it is should format email correct for example: xxxxx@xxx.com")
    private String email;


    @ApiModelProperty(notes = "it is necessary to enter customer id", example = "1", required = true)
    @Min(value = 1, message = "the minimum duty value 1 or customerId should to be a value valid")
    private long customerId;
}
