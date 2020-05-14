package com.email.apirest.controller.Mock;

import com.email.apirest.dto.*;

import java.util.ArrayList;
import java.util.List;

public class MockEmail {

    public static EmailGETById getEmailGETById(){

        EmailGETById emailGETById = new EmailGETById();
        emailGETById.setEmail("lenercab@gmail.com");
        emailGETById.setId(1l);

        return emailGETById;
    }

    public static EmailGETALL getEmailGETALL(){

        EmailGETById emailGETById = new EmailGETById();
        emailGETById.setEmail("lenercab@gmail.com");
        emailGETById.setId(1l);
        List<EmailGETById> emails = new ArrayList<>();
        emails.add(emailGETById);
        EmailGETALL emailGETALL = new EmailGETALL();
        emailGETALL.setEmails(emails);
        return emailGETALL;
    }

    public static EmailPSTRq getEmailPSTRq(){

        EmailPSTRq emailPSTRq = new EmailPSTRq();
        emailPSTRq.setId(1l);
        emailPSTRq.setEmail("lenercab@gmail.com");
        emailPSTRq.setCustomerId(1l);

        return emailPSTRq;
    }

    public static EmailPSTRs getEmailPSTRs(){

        EmailPSTRs emailPSTRs = new EmailPSTRs();
        emailPSTRs.setEmailId(1l);

        return emailPSTRs;
    }

    public static EmailPTCRq getEmailPTCRq(){

        EmailPTCRq emailPTCRq = new EmailPTCRq();
        emailPTCRq.setId(1l);
        emailPTCRq.setEmail("lenercab@gmail.com");
        emailPTCRq.setCustomerId(1l);

        return emailPTCRq;
    }

    public static EmailPTCRs getEmailPTCRs(){

        EmailPTCRs emailPTCRs = new EmailPTCRs();
        emailPTCRs.setEmailId(1l);

        return emailPTCRs;
    }
}
