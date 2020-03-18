package com.address.apirest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressGETrs {

    @ApiModelProperty(example = "1")
    private long id;

    @ApiModelProperty(example = "Kra75 #52a-86")
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

    @ApiModelProperty(example = "1")
    private long customerId;
}
