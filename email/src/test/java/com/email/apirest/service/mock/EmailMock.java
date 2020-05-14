package com.email.apirest.service.mock;

import com.email.apirest.dto.EmailPTCRq;
import com.email.apirest.model.Email;

public class EmailMock {

    public static EmailPTCRq getEmailPTCrq() {

        EmailPTCRq email = new EmailPTCRq();
        email.setId(1l);
        email.setEmail("lenercab@gmail.com");
        email.setCustomerId(1l);

        return email;
    }
}
