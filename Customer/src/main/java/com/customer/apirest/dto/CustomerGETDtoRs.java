package com.customer.apirest.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerGETDtoRs {


    private Long CustomerId;

    private IdentificationDto identification;

    private String name;

    private String lastName;

    private String country;


    private String birthCity;

    private LocalDate birthDate;

    private String maritalStatus;

    private GenderDto gender;

    private String profession;


}
