package com.phone.phone.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel(description = "This model class represents the data basic one phone")
@Data
public class PhonePTCrq {

    @ApiModelProperty(example = "1")
    private long id;

    @ApiModelProperty(notes = "it is necessary to enter number phone", example = "3135718791", required = true)
    @NotNull(message = "the field numberPhone is required")
    @NotEmpty(message = "numberPhone: empty field is not accepted")
    @Pattern(regexp = "^\\d+$", message = "the numberPhone field should be numeric")
    private String numberPhone;

    @ApiModelProperty(example = "Movil")
    @NotNull(message = "the field numberPhone is required")
    @NotEmpty(message = "numberPhone: empty field is not accepted")
    private String typePhone;

    @ApiModelProperty(example = "Tigo")
    private String operator;

    @ApiModelProperty(notes = "it is necessary to enter customer id", example = "1", required = true)
    @Min(value = 1, message = "the minimum duty value 1 or customerId should to be a value valid")
    private long customerId;
}
