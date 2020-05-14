package com.customer.apirest.controller;


import com.customer.apirest.dto.CustomerPSTDtoRq;
import com.customer.apirest.dto.CustomerPSTDtoRs;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

public class Hateoas {

    public static void getHateoasCustomerPST(CustomerPSTDtoRs CustomerPSTDtoRs, CustomerPSTDtoRq customerPSTDtoRq) {
        CustomerPSTDtoRs.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CustomersController.class).CustomersPST(customerPSTDtoRq,"lener-Aspire-E5-475")).withSelfRel());
        getHateoasCustomer(CustomerPSTDtoRs, customerPSTDtoRq);
    }

    public static void getHateoasCustomerPTC(CustomerPSTDtoRs CustomerPSTDtoRs, CustomerPSTDtoRq customerPSTDtoRq) {
        CustomerPSTDtoRs.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CustomersController.class).CustomerPTC(customerPSTDtoRq, "lener-Aspire-E5-475")).withSelfRel());
        getHateoasCustomer(CustomerPSTDtoRs, customerPSTDtoRq);
    }

    public static  void getHateoasCustomer(CustomerPSTDtoRs CustomerPSTDtoRs,CustomerPSTDtoRq customerPSTDtoRq ) {

        CustomerPSTDtoRs.add(
                ControllerLinkBuilder
                        .linkTo(ControllerLinkBuilder.methodOn(CustomersController.class)
                                .CustomersGETById(CustomerPSTDtoRs.getCustomerId(), "lener-Aspire-E5-475"))
                        .withRel("customerGetById"));
        CustomerPSTDtoRs.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(CustomersController.class).CustomerGetByNumberIdentification(
                        customerPSTDtoRq.getIdentification().getNumberIdentification(),
                        customerPSTDtoRq.getIdentification().getIdentificationType().getCode()))
                .withRel("customerGetByIdentification"));
        CustomerPSTDtoRs.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(CustomersController.class).CustomerPTC(customerPSTDtoRq, "lener-Aspire-E5-475"))
                .withRel("customerPTC"));
        CustomerPSTDtoRs.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(CustomersController.class)
                        .CustomersDeleteById(customerPSTDtoRq.getCustomerId(), "lener-Aspire-E5-475"))
                .withRel("customeDelete"));
    }
}