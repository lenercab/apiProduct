package com.customer.apirest.util;

import com.customer.apirest.dto.CustomerPSTDtoRq;
import com.customer.apirest.model.Customer;
import com.customer.apirest.model.Gender;
import com.customer.apirest.model.Identification;
import com.customer.apirest.model.IdentificationType;

public class Mapper {

    public static Customer mapperRequest(CustomerPSTDtoRq customerPSTDtoRq){

        Customer customer = new Customer();

        Identification identification = new Identification();

        IdentificationType identificationType = new IdentificationType();

        identification.setNumberIdentification(customerPSTDtoRq.getIdentification().getNumberIdentification());
        identification.setExpeditionDate(customerPSTDtoRq.getIdentification().getExpeditionDate());
        identification.setExpeditionCity(customerPSTDtoRq.getIdentification().getExpeditionCity());
        identificationType.setCode(customerPSTDtoRq.getIdentification().getIdentificationType().getCode());
        identification.setIdentificationType(identificationType);

        customer.setIdentification(identification);
        customer.setName(customerPSTDtoRq.getName());
        customer.setLastName(customerPSTDtoRq.getLastName());
        customer.setBirthDate(customerPSTDtoRq.getBirthDate());
        customer.setBirthCity(customerPSTDtoRq.getBirthCity());
        customer.setCountry(customerPSTDtoRq.getCountry());
        customer.setMaritalStatus(customerPSTDtoRq.getMaritalStatus());
        customer.setProfession(customerPSTDtoRq.getProfession());
        Gender gender = new Gender();
        gender.setCode(customerPSTDtoRq.getGender().getCode());
        customer.setGender(gender);

        return customer;
    }
}
