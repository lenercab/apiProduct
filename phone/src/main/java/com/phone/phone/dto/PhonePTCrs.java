package com.phone.phone.dto;

import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

@Data
public class PhonePTCrs extends ResourceSupport {

    private long phoneId;

}
