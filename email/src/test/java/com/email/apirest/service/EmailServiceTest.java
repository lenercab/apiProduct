package com.email.apirest.service;

import com.email.apirest.dto.*;
import com.email.apirest.exception.CustomerNotFoundExpection;
import com.email.apirest.exception.EmailNotFoundException;
import com.email.apirest.model.Email;
import com.email.apirest.repository.CustomerRepository;
import com.email.apirest.repository.EmailRepository;
import com.email.apirest.util.Mapper;
import mockit.Deencapsulation;
import mockit.MockUp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class EmailServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    EmailRepository emailRepository;


    @InjectMocks
    EmailService emailService;

    @Test
    public void getAllEmails() {

        when(customerRepository.existsById(1l)).thenReturn(true);
        when(emailRepository.findAllByCustomerId(1l)).thenReturn(getListEmails());

        EmailGETALL emailGETALL = emailService.getAllEmails(1l);
        assertEquals(false, emailGETALL.getEmails().isEmpty());
    }

    @Test(expected = CustomerNotFoundExpection.class)
    public void getAllEmails_when_not_found_customer() {

        when(customerRepository.existsById(1l)).thenReturn(false);
        emailService.getAllEmails(1l);

    }

    @Test(expected = EmailNotFoundException.class)
    public void getAllEmails_when_not_found_email() {

        when(customerRepository.existsById(1l)).thenReturn(true);
        when(emailRepository.findAllByCustomerId(1l)).thenReturn(new ArrayList<>());

        EmailGETALL emailGETALL = emailService.getAllEmails(1l);
        assertEquals(true, emailGETALL.getEmails().isEmpty());
    }

    @Test
    public void getByIdEmail() {

        when(customerRepository.existsById(1l)).thenReturn(true);
        when(emailRepository.findByIdandCustomerId(1l, 1l)).thenReturn(getEmail());

        EmailGETById emailGETById = emailService.getByIdEmail(1l, 1l);
        assertNotNull(emailGETById);
    }

    @Test(expected = CustomerNotFoundExpection.class)
    public void getByIdEmail_when_not_found_customer() {

        when(customerRepository.existsById(1l)).thenReturn(false);
        emailService.getByIdEmail(1l, 1l);

    }

    @Test(expected = EmailNotFoundException.class)
    public void getByIdEmail_when_not_found_email() {

        when(customerRepository.existsById(1l)).thenReturn(true);
        when(emailRepository.findByIdandCustomerId(1l, 1l)).thenReturn(null);

        emailService.getByIdEmail(1l, 1l);

    }

    @Test
    public void saveEmail() {

        when(customerRepository.existsById(1l)).thenReturn(true);
        Email email = getEmail();
        EmailPSTRq emailPSTRq = getEmailPSTrq();
        new MockUp<Mapper>() {
            @mockit.Mock
            public Email mapperRequestPST(EmailPSTRq emailPSTRq) {
                return email;
            }
        };
        Deencapsulation.invoke(Mapper.class, "mapperRequestPST", emailPSTRq);
        when(emailRepository.save(email)).thenReturn(email);

        EmailPSTRs emailPSTRs = emailService.saveEmail(getEmailPSTrq());

        assertEquals(1l, emailPSTRs.getEmailId());
    }


    @Test(expected = CustomerNotFoundExpection.class)
    public void saveEmail_when_not_found_customer() {

        when(customerRepository.existsById(1l)).thenReturn(false);
        EmailPSTRq emailPSTRq = getEmailPSTrq();
        emailService.saveEmail(emailPSTRq);

    }

    @Test
    public void updateEmail() {

        when(customerRepository.existsById(1l)).thenReturn(true);
        when(emailRepository.ExistsByIdAndCustomerId(1l, 1l)).thenReturn(1);
        Email email = getEmail();
        EmailPTCRq emailPTCRq = getEmailPTCrq();
        new MockUp<Mapper>() {
            @mockit.Mock
            public Email mapperRequestPTC(EmailPTCRq emailPTCRq) {
                return email;
            }
        };
        Deencapsulation.invoke(Mapper.class, "mapperRequestPTC", emailPTCRq);
        when(emailRepository.save(email)).thenReturn(email);
        EmailPTCRs emailPTCRs = emailService.updateEmail(emailPTCRq);

        assertEquals(1l, emailPTCRs.getEmailId());
    }

    @Test(expected = CustomerNotFoundExpection.class)
    public void updateEmail_when_not_found_customer() {

        when(customerRepository.existsById(1l)).thenReturn(false);
        EmailPTCRq emailPTCRq = getEmailPTCrq();
        emailService.updateEmail(emailPTCRq);

    }

    @Test(expected = EmailNotFoundException.class)
    public void updateEmail_when_not_exist_email() {

        when(customerRepository.existsById(1l)).thenReturn(true);
        when(emailRepository.ExistsByIdAndCustomerId(1l, 1l)).thenReturn(0);
        EmailPTCRq emailPTCRq = getEmailPTCrq();
        emailService.updateEmail(emailPTCRq);

    }

    @Test
    public void deleteEmailById() {
        when(emailRepository.existsById(1l)).thenReturn(true);
        Message message = emailService.deleteEmailById(1l, 1l);
        assertEquals("fallo test eliminacion de email", "Email deleted successfully: id: 1", message.getMessage());
    }


    @Test(expected = EmailNotFoundException.class)
    public void deleteEmailById_when_not_exist_email() {
        when(emailRepository.existsById(1l)).thenReturn(false);
        Message message = emailService.deleteEmailById(1l, 1l);
    }

    public List<Email> getListEmails() {

        Email email = new Email();
        email.setId(1l);
        email.setEmail("lenercab@gmail.com");
        email.setCustomerId(1l);

        List<Email> emails = new ArrayList<>();

        emails.add(email);

        return emails;
    }

    public Email getEmail() {

        Email email = new Email();
        email.setId(1l);
        email.setEmail("lenercab@gmail.com");
        email.setCustomerId(1l);

        return email;
    }

    public EmailPSTRq getEmailPSTrq() {

        EmailPSTRq email = new EmailPSTRq();
        email.setId(1l);
        email.setEmail("lenercab@gmail.com");
        email.setCustomerId(1l);

        return email;
    }

    public EmailPTCRq getEmailPTCrq() {

        EmailPTCRq email = new EmailPTCRq();
        email.setId(1l);
        email.setEmail("lenercab@gmail.com");
        email.setCustomerId(1l);

        return email;
    }
}