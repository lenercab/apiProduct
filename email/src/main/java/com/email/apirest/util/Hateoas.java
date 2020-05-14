package com.email.apirest.util;

import com.email.apirest.controller.EmailController;
import com.email.apirest.dto.EmailPSTRq;
import com.email.apirest.dto.EmailPSTRs;
import com.email.apirest.dto.EmailPTCRq;
import com.email.apirest.dto.EmailPTCRs;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

public class Hateoas {

    public static void getHateoasEmailPST(EmailPSTRs emailPSTRs, EmailPSTRq emailPSTRq) {
        emailPSTRs.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(EmailController.class).emailPST(emailPSTRq)).withSelfRel());

        emailPSTRs.add(
                ControllerLinkBuilder
                        .linkTo(ControllerLinkBuilder.methodOn(EmailController.class)
                                .getAllEmails(emailPSTRq.getCustomerId()))
                        .withRel("emailGetAll"));
        emailPSTRs.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(EmailController.class).getByIdEmail(
                        emailPSTRq.getCustomerId(),emailPSTRs.getEmailId()))
                .withRel("emailGEtById"));
        emailPSTRs.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(EmailController.class).emailPTC(new EmailPTCRq()))
                .withRel("emailPTC"));
        emailPSTRs.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(EmailController.class)
                        .emailDeleteById(emailPSTRs.getEmailId(), emailPSTRq.getCustomerId()))
                .withRel("emailDelete"));

    }

    public static void getHateoasEmailPTC(EmailPTCRs emailPTCRs, EmailPTCRq emailPTCRq) {
        emailPTCRs.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(EmailController.class).emailPTC(emailPTCRq)).withSelfRel());

        emailPTCRs.add(
                ControllerLinkBuilder
                        .linkTo(ControllerLinkBuilder.methodOn(EmailController.class)
                                .getAllEmails(emailPTCRq.getCustomerId()))
                        .withRel("emailGetAll"));
        emailPTCRs.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(EmailController.class).getByIdEmail(
                        emailPTCRq.getCustomerId(),emailPTCRs.getEmailId()))
                .withRel("emailGEtById"));
        emailPTCRs.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(EmailController.class).emailPST(new EmailPSTRq()))
                .withRel("emailPST"));
        emailPTCRs.add(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(EmailController.class)
                        .emailDeleteById(emailPTCRs.getEmailId(), emailPTCRq.getCustomerId()))
                .withRel("emailDelete"));

    }
}
