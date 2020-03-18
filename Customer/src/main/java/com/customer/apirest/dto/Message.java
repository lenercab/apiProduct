package com.customer.apirest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Message {

    @ApiModelProperty(example = "Customer deleted successfully id: 1")
    private String message;
}
