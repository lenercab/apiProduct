package com.customer.apirest.dto;

import lombok.Data;
import org.springframework.hateoas.ResourceSupport;


@Data
public class CustomerPSTDtoRs extends ResourceSupport {

    private Long CustomerId;

}
