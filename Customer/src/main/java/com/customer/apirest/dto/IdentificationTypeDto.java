package com.customer.apirest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

@ApiModel(description = "This model class is representing the identification type of the customer")
@Data
public class IdentificationTypeDto {

    @ApiModelProperty(notes = "The unique values accepted were CC, TI or CE", example = "CC", required = true, position = 1)
    @Pattern(regexp = "^CC|TI|CE$", message = "the values of the field code of the object identificationType should be CC|TI|CE")
    private String code;

    @ApiModelProperty(hidden = true)
    private String identificationType;

}
