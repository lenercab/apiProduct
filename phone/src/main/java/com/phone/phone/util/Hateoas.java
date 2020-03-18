package com.phone.phone.util;

import com.phone.phone.controller.PhoneController;
import com.phone.phone.dto.PhonePSTrq;
import com.phone.phone.dto.PhonePSTrs;
import com.phone.phone.dto.PhonePTCrq;
import com.phone.phone.dto.PhonePTCrs;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

public class Hateoas {

    public static void getHateoasPhonePST(PhonePSTrs phonePSTRs, PhonePSTrq phonePSTRq) {
        phonePSTRs.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(PhoneController.class).PhonePST(phonePSTRq)).withSelfRel());

        phonePSTRs.add(
                ControllerLinkBuilder
                        .linkTo(ControllerLinkBuilder.methodOn(PhoneController.class)
                                .getAllPhonesByCustomerId(phonePSTRq.getCustomerId()))
                        .withRel("phoneGETAll"));
        phonePSTRs.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(PhoneController.class).getPhonesByIdAndCustomerId(
                        phonePSTRs.getPhoneId(), phonePSTRq.getCustomerId()))
                .withRel("phoneGET"));
        phonePSTRs.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(PhoneController.class).PhonePTC(new PhonePTCrq()))
                .withRel("phonePTC"));
        phonePSTRs.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(PhoneController.class)
                        .deletePhonesById(phonePSTRs.getPhoneId(), phonePSTRq.getCustomerId()))
                .withRel("phoneDelete"));
    }

    public static void getHateoasPhonePTC(PhonePTCrs phonePTCRs, PhonePTCrq phonePTCRq) {
        phonePTCRs.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(PhoneController.class).PhonePTC(phonePTCRq)).withSelfRel());

        phonePTCRs.add(
                ControllerLinkBuilder
                        .linkTo(ControllerLinkBuilder.methodOn(PhoneController.class)
                                .getAllPhonesByCustomerId(phonePTCRq.getCustomerId()))
                        .withRel("phoneGETAll"));
        phonePTCRs.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(PhoneController.class).getPhonesByIdAndCustomerId(
                        phonePTCRs.getPhoneId(), phonePTCRq.getCustomerId()))
                .withRel("phoneGET"));
        phonePTCRs.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(PhoneController.class).PhonePST(new PhonePSTrq()))
                .withRel("phonePST"));
        phonePTCRs.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(PhoneController.class)
                        .deletePhonesById(phonePTCRs.getPhoneId(), phonePTCRq.getCustomerId()))
                .withRel("phoneDelete"));
    }
}