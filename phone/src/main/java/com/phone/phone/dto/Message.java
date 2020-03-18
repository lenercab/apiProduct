package com.phone.phone.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Message {

    @ApiModelProperty(example = "Phone deleted successfully")
    private String message;
}
