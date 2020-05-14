package com.email.apirest.dto;

import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

@Data
public class EmailPTCRs extends ResourceSupport {

    private long emailId;
}

