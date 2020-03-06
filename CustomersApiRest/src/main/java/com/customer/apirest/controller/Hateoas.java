package com.customer.apirest.controller;

import com.customer.apirest.model.Customer;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;

public class Hateoas {

    public static void getHateoasCustomerPST(Customer customer) {
        customer.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CustomersController.class).CustomersPST(customer,"lener-Aspire-E5-475")).withSelfRel());
        getHateoasCustomer(customer);
    }

    public static void getHateoasCustomerPTC(Customer customer) {
        customer.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CustomersController.class).CustomerPTC(customer, "lener-Aspire-E5-475")).withSelfRel());
        getHateoasCustomer(customer);
    }

    public static  void getHateoasCustomer(Customer customer) {

        customer.add(
                ControllerLinkBuilder
                        .linkTo(ControllerLinkBuilder.methodOn(CustomersController.class)
                                .CustomersGETById(customer.getCustomerId(), "lener-Aspire-E5-475"))
                        .withRel("customerGetById"));
        customer.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(CustomersController.class).CustomerGetByNumberIdentification(
                        customer.getIdentification().getNumberIdentification(),
                        customer.getIdentification().getIdentificationType().getCode()))
                .withRel("customerGetByIdentification"));
        customer.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(CustomersController.class).CustomerPTC(customer, "lener-Aspire-E5-475"))
                .withRel("customerPTC"));
        customer.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(CustomersController.class)
                        .CustomersDeleteById(customer.getCustomerId(), "lener-Aspire-E5-475"))
                .withRel("customeDelete"));
    }
}