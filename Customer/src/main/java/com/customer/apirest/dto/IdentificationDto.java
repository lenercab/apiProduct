package com.customer.apirest.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@ApiModel(description = "This model class represents the information basic of the identification customer")
@Data
public class IdentificationDto {

    @ApiModelProperty(example = "1")
    private long id;

    @ApiModelProperty(notes = "it is necessary to enter an identification number", example = "9297638", required = true)
    @Pattern(regexp = "^\\d+$", message = "the numberIdentification field should be numeric")
    private String numberIdentification;

    @Valid
    private IdentificationTypeDto identificationType;

    @ApiModelProperty(notes = "this is date expedition of the document", example = "2000-11-20")
    private LocalDate expeditionDate;

    @ApiModelProperty(notes = "document issuing city", example = "Cartagena")
    private String expeditionCity;

}
