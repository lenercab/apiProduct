package com.customer.apirest.controller;

import com.customer.apirest.CustomersApiRestApplication;
import com.customer.apirest.model.Customer;
import com.customer.apirest.model.Gender;
import com.customer.apirest.model.Identification;
import com.customer.apirest.model.IdentificationType;
import com.customer.apirest.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CustomersApiRestApplication.class)
@WebAppConfiguration
public class CustomersControllerTest {

    @MockBean
    CustomerService customerService;

    protected MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;


    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    public void CustomersGETById() throws Exception {

        Optional optional = serviceCustomerMock();

        when(customerService.findById(1L)).thenReturn(optional);

        String uri = "/Customers/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).header("x-terminal", "Lener-pc")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("status code incorrect", 200, status);
        assertNotNull(content);
    }

//    @Test
//    public void CustomersPST() throws Exception {
//
//        Customer customer = serviceCustomerMock_3();
//        when(customerService.insertCustomer(customer)).thenReturn(customer);
//
//        String uri = "/Customers";
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).header("x-terminal", "Lener-pc")
//                .content(asJsonString(customer))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//        int status = mvcResult.getResponse().getStatus();
//        String content = mvcResult.getResponse().getContentAsString();
//        assertEquals("status code incorrect", 201, status);
//        assertNotNull(content);
//
//    }

    @Test
    public void CustomerGetByIdentification() throws Exception {

        Customer customer = serviceCustomerMock_2();
        when(customerService.findByNumberIdentification("9297638", "CC")).thenReturn(customer);

        String uri = "/Customers/CC/9297638";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).header("x-terminal", "Lener-pc")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("status code incorrect", 200, status);
        assertNotNull(content);

    }


    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    public Customer serviceCustomerMock_2() {

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

    public Customer serviceCustomerMock_3() {

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

        customer.setGender(gender);

        IdentificationType identificationType = new IdentificationType();
        identificationType.setCode("CC");

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