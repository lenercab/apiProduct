package com.address.apirest.repository;

import com.address.apirest.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Test
    public void CRUDTestAddress(){
      Address address = getRequestAddress();
      addressRepository.save(address);

      assertEquals(1l, address.getId());
      assertEquals("Kra 75 #52a-86", addressRepository.findByIdAndCustomerId(1l, 1l).getAddress());
      assertEquals(false, addressRepository.findAllByCustomerId(1l).isEmpty());

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

    public Address getRequestAddress(){

        Address address = new Address();
        address.setAddress("Kra 75 #52a-86");
        address.setCountry("Colombia");
        address.setCity("Bogota D.C");
        address.setLocation("Engativa");
        address.setStreet("Normandia");
        address.setMainAddress(true);
        address.setCustomerId(1l);

        return address;
    }

}