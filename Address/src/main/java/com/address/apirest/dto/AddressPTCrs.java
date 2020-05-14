package com.address.apirest.dto;

import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

@Data
public class AddressPTCrs extends ResourceSupport {

    private long addressId;

}
