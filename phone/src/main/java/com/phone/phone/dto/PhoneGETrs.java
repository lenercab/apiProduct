package com.phone.phone.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhoneGETrs {

    @ApiModelProperty(example = "1")
    private long id;

    @ApiModelProperty(example = "3135718791")
    private String numberPhone;

    @ApiModelProperty(example = "Movil")
    private String typePhone;

    @ApiModelProperty(example = "Tigo")
    private String operator;

    @ApiModelProperty(example = "1")
    private long customerId;
}
