package com.address.apirest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Message {

    @ApiModelProperty(example = "Address deleted successfully")
    private String message;
}
