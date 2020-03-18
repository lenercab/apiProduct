package com.email.apirest.dto;

import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

@Data
public class EmailPSTRs extends ResourceSupport {

    private long emailId;
}
