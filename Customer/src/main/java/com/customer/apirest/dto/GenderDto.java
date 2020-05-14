package com.customer.apirest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

@ApiModel(description = "This model class represents the gender of the customer")
@Data
public class GenderDto {

    @ApiModelProperty(notes = "The unique values accepted were M or F", example = "M", required = true, position = 1)
    @Pattern(regexp = "^M|F$", message = "the field code of gender should be M or F")
    private String code;

    @ApiModelProperty(hidden = true)
    private String description;

}
