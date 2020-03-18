package com.email.apirest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Message {

    @ApiModelProperty(example = "Email deleted successfully: id: 1")
    private String message;
}
