package com.phone.phone.repository;

import com.phone.phone.model.*;
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
public class PhoneRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @Test
    public void CRUDtestPhone(){

        Customer customer = getRequestCustomer();
        customerRepository.save(customer);
        Phone phone = getRequestPhone();

        phoneRepository.save(phone);

        assertEquals(1l, phone.getId());
        assertEquals(false, phoneRepository.findAllByCustomerId(1l).isEmpty());
        assertEquals("3135718791", phoneRepository.findByIdAndCustomerId(1l, 1l).getNumberPhone());
        assertEquals(1, phoneRepository.exitNumberPhoneOfCustomer("3135718791", 1l));
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

    public Phone getRequestPhone(){

        Phone phone = new Phone();
        phone.setNumberPhone("3135718791");
        phone.setTypePhone("Movil");
        phone.setOperator("CLARO");
        phone.setCustomerId(1l);

        return phone;
    }

}