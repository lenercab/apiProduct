package com.customer.apirest.service;

import com.customer.apirest.model.Customer;
import com.customer.apirest.model.Gender;
import com.customer.apirest.model.Identification;
import com.customer.apirest.model.IdentificationType;
import com.customer.apirest.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService;

    @Test
    public void insertCustomer() {

        Customer customer = serviceCustomerMockGetByIdentification();

        when(customerRepository.save(customer)).thenReturn(customer);
        Customer actual = customerService.insertCustomer(customer);

        assertEquals(customer, actual);
    }

    @Test
    public void findById() {

        Optional expected = serviceCustomerMock();

        when(customerRepository.findById(1L)).thenReturn(expected);
        Optional actual = customerService.findById(1L);

        assertEquals(expected, actual);


    }

    @Test
    public void findByNumberIdentification() {
        Customer expected = serviceCustomerMockGetByIdentification();

        when(customerRepository.findByNumerIdentification("9297638", "CC")).thenReturn(expected);
        Customer actual = customerService.findByNumberIdentification("9297638", "CC");

        assertEquals(expected, actual);

    }

    @Test
    public void updateCustomer() {
    }

    @Test
    public void deleteCustomer() {
    }

    public Optional serviceCustomerMock() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setName("Leehener");
        customer.setLastName("Cabeza");
        customer.setBirthCity("Cartagena");
        customer.setCountry("Colombia");
        customer.setMaritalStatus("Soltero");
        customer.setProfession("System Engineer");
        customer.setBirthDate(LocalDate.parse("1982-04-24", formatter));

        Gender gender = new Gender();
        gender.setCode("M");
        gender.setDescription("Masculino");
        customer.setGender(gender);

        IdentificationType identificationType = new IdentificationType();
        identificationType.setCode("CC");
        identificationType.setIdentificationType("Cedula de Ciudadania");
        Identification identification = new Identification();
        identification.setIdentificationType(identificationType);
        identification.setId(1L);
        identification.setNumberIdentification("9297638");
        identification.setExpeditionCity("Bolivar");
        identification.setExpeditionDate(LocalDate.parse("2000-11-20", formatter));
        customer.setIdentification(identification);


        Optional<Customer> optional = Optional.of(customer);
        return optional;
    }

    public Customer serviceCustomerMockGetByIdentification() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setName("Leehener");
        customer.setLastName("Cabeza");
        customer.setBirthCity("Cartagena");
        customer.setCountry("Colombia");
        customer.setMaritalStatus("Soltero");
        customer.setProfession("System Engineer");
        customer.setBirthDate(LocalDate.parse("1982-04-24", formatter));

        Gender gender = new Gender();
        gender.setCode("M");
        gender.setDescription("Masculino");
        customer.setGender(gender);

        IdentificationType identificationType = new IdentificationType();
        identificationType.setCode("CC");
        identificationType.setIdentificationType("Cedula de Ciudadania");
        Identification identification = new Identification();
        identification.setIdentificationType(identificationType);
        identification.setId(1L);
        identification.setNumberIdentification("9297638");
        identification.setExpeditionCity("Bolivar");
        identification.setExpeditionDate(LocalDate.parse("2000-11-20", formatter));
        customer.setIdentification(identification);

        return customer;
    }
}