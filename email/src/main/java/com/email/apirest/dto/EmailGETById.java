package com.email.apirest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailGETById {

    @ApiModelProperty(example = "1")
    private long id;
    @ApiModelProperty(example = "leehcab@hotmail.com")
    private String email;
}
