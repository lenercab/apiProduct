package com.address.apirest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class AddressPTCrq {

    @ApiModelProperty(example = "1", required = true)
    private long id;

    @ApiModelProperty(example = "Kra 75 #52a - 85", required = true)
    private String address;

    @ApiModelProperty(example = "Colombia")
    private String country;

    @ApiModelProperty(example = "Bogota D.C")
    private String city;

    @ApiModelProperty(example = "Engativa")
    private String location;

    @ApiModelProperty(example = "Normandia")
    private String street;

    @ApiModelProperty(example = "true")
    private boolean mainAddress;

    @ApiModelProperty(notes = "it is necessary to enter customer id", example = "1", required = true)
    @Min(value = 1, message = "the minimum duty value 1 or customerId should to be a value valid")
    private long customerId;
}
