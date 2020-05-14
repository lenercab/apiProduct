package com.email.apirest.repository;

import com.email.apirest.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EmailRepository emailRepository;

    @Test
    public void saveEmail() {
        Customer customer = getRequestCustomer();
        customerRepository.save(customer);
        Email email = emailRepository.save(getRequestEmail());
        assertEquals(1l, email.getId());

    }

    @Test
    public void findByIdandCustomerId() {
        Email email = emailRepository.findByIdandCustomerId(1l, 1l);
        assertEquals(1, email.getId());

        List<Email> emails =  emailRepository.findAllByCustomerId(1l);
        assertEquals(1, emails.size());
    }

    @Test
    public void ExistsByIdAndCustomerId(){
        int countEmails =  emailRepository.ExistsByIdAndCustomerId(1l,1l);
        assertEquals(1, countEmails);
    }

    public Customer getRequestCustomer() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Customer customer = new Customer();

        customer.setName("Leehener");
        customer.setLastName("Cabeza");
        customer.setBirthCity("Cartagena");
        customer.setCountry("Colombia");
        customer.setMaritalStatus("Soltero");
        customer.setProfession("System Engineer");
        customer.setBirthDate(LocalDate.parse("1982-04-24", formatter));

        Gender gender = new Gender();
        gender.setCode("M");
        customer.setGender(gender);

        IdentificationType identificationType = new IdentificationType();
        identificationType.setCode("CC");
        Identification identification = new Identification();
        identification.setIdentificationType(identificationType);
        identification.setNumberIdentification("9297638");
        identification.setExpeditionCity("Bolivar");
        identification.setExpeditionDate(LocalDate.parse("2000-11-20", formatter));
        customer.setIdentification(identification);

        return customer;
    }

    public Email getRequestEmail() {

        Email email = new Email();
        email.setEmail("lenercab@gmail.com");
        email.setCustomerId(1l);

        return email;
    }

}