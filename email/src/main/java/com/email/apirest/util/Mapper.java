package com.email.apirest.util;

import com.email.apirest.dto.EmailPSTRq;
import com.email.apirest.dto.EmailPTCRq;
import com.email.apirest.model.Email;

public class Mapper {

    public static Email mapperRequestPST(EmailPSTRq emailPSTRq){

        Email email = new Email();
        email.setCustomerId(emailPSTRq.getCustomerId());
        email.setEmail(emailPSTRq.getEmail());

        return email;
    }

    public static Email mapperRequestPTC(EmailPTCRq emailPTCRq){

        Email email = new Email();
        email.setCustomerId(emailPTCRq.getCustomerId());
        email.setEmail(emailPTCRq.getEmail());
        email.setId(emailPTCRq.getId());

        return email;
    }
}
