package com.address.apirest.util;

import com.address.apirest.controller.AddressController;
import com.address.apirest.dto.AddressPSTrq;
import com.address.apirest.dto.AddressPSTrs;
import com.address.apirest.dto.AddressPTCrq;
import com.address.apirest.dto.AddressPTCrs;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

public class Hateoas {

    public static void getHateoasAddressPST(AddressPSTrs addressPSTrs, AddressPSTrq addressPSTrq) {
        addressPSTrs.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(AddressController.class).AddressPST(addressPSTrq)).withSelfRel());

        addressPSTrs.add(
                ControllerLinkBuilder
                        .linkTo(ControllerLinkBuilder.methodOn(AddressController.class)
                                .getAllAddressesByCustomerId(addressPSTrq.getCustomerId()))
                        .withRel("GET"));
        addressPSTrs.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(AddressController.class).getAddressByIdAndCustomerId(
                        addressPSTrs.getAddressId(), addressPSTrq.getCustomerId()))
                .withRel("GET"));
        addressPSTrs.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(AddressController.class).AddressPTC(new AddressPTCrq()))
                .withRel("PATCH"));
        addressPSTrs.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(AddressController.class)
                        .deleteAddressById(addressPSTrs.getAddressId(), addressPSTrq.getCustomerId()))
                .withRel("DELETE"));
    }

    public static void getHateoasAddressPTC(AddressPTCrs addressPTCrs, AddressPTCrq addressPTCrq) {
        addressPTCrs.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(AddressController.class).AddressPTC(addressPTCrq)).withSelfRel());

        addressPTCrs.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(AddressController.class)
                        .getAllAddressesByCustomerId(addressPTCrq.getCustomerId()))
                .withRel("GET"));
        addressPTCrs.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(AddressController.class)
                        .getAddressByIdAndCustomerId(addressPTCrs.getAddressId(), addressPTCrq.getCustomerId()))
                .withRel("GET"));
        addressPTCrs.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(AddressController.class)
                        .AddressPST(new AddressPSTrq()))
                .withRel("POST"));
        addressPTCrs.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(AddressController.class)
                        .deleteAddressById(addressPTCrs.getAddressId(), addressPTCrq.getCustomerId()))
                .withRel("DELETE"));
    }
}
