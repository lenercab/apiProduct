package com.customer.apirest.service;

import com.customer.apirest.dto.*;
import com.customer.apirest.exception.CustomerExistException;
import com.customer.apirest.exception.CustomerNotFoundExpection;
import com.customer.apirest.model.Customer;
import com.customer.apirest.model.Gender;
import com.customer.apirest.model.Identification;
import com.customer.apirest.model.IdentificationType;
import com.customer.apirest.repository.CustomerRepository;
import com.customer.apirest.util.Mapper;
import mockit.Deencapsulation;
import mockit.MockUp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static com.customer.apirest.util.Mapper.mapperRequest;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService;

    @Test
    public void insertCustomer() {

        CustomerPSTDtoRq customerPSTDtoRq = serviceCustomerMockGetByIdentification();

        Customer customer = ConvertFromCustomerPSTDtoRqtoCustomer(customerPSTDtoRq);

        when(customerRepository.existIdentification(customerPSTDtoRq.getIdentification().getNumberIdentification(),
                customerPSTDtoRq.getIdentification().getIdentificationType().getCode())).thenReturn(0);

        new MockUp<Mapper>() {
            @mockit.Mock
            public Customer mapperRequest(CustomerPSTDtoRq customerPSTDtoRq) {
                return customer;
            }
        };
        Deencapsulation.invoke(Mapper.class, "mapperRequest", customerPSTDtoRq);
        when(customerRepository.save(customer)).thenReturn(customer);

        CustomerPSTDtoRs actual = customerService.insertCustomer(customerPSTDtoRq);

        assertEquals(customer.getCustomerId(), actual.getCustomerId());
    }

    @Test(expected = CustomerExistException.class)
    public void insertCustomer_when_Already_exists_customer() {
        CustomerPSTDtoRq customerPSTDtoRq = serviceCustomerMockGetByIdentification();
        Customer customer = ConvertFromCustomerPSTDtoRqtoCustomer(customerPSTDtoRq);
        when(customerRepository.existIdentification(customerPSTDtoRq.getIdentification().getNumberIdentification(),
                customerPSTDtoRq.getIdentification().getIdentificationType().getCode())).thenReturn(1);
        new MockUp<Mapper>() {
            @mockit.Mock
            public Customer mapperRequest(CustomerPSTDtoRq customerPSTDtoRq) {
                return customer;
            }
        };
        Deencapsulation.invoke(Mapper.class, "mapperRequest", customerPSTDtoRq);
        customerService.insertCustomer(customerPSTDtoRq);

    }

    @Test
    public void findById() {

        CustomerPSTDtoRq customerPSTDtoRq = serviceCustomerMockGetByIdentification();
        Customer customer = ConvertFromCustomerPSTDtoRqtoCustomer(customerPSTDtoRq);
        CustomerGETDtoRs customerGETDtoRs = getCustomerGETDtoRs(customerPSTDtoRq);

        when(customerRepository.existsById(1L)).thenReturn(true);
        when(customerRepository.findByCustomerId(1L)).thenReturn(customer);

        CustomerGETDtoRs actual = customerService.findById(1L);

        assertEquals("Leehener", actual.getName());


    }

    @Test(expected = CustomerNotFoundExpection.class)
    public void findById_when_not_found_customer() {

        CustomerPSTDtoRq customerPSTDtoRq = serviceCustomerMockGetByIdentification();
        when(customerRepository.existsById(1L)).thenReturn(false);

        customerService.findById(1L);

    }


    @Test
    public void findByNumberIdentification() {
        Customer expected = getCustomerMock();

        when(customerRepository.findByNumerIdentification("9297638", "CC")).thenReturn(expected);
        CustomerGETDtoRs actual = customerService.findByNumberIdentification("9297638", "CC");

        assertEquals(expected.getName(), actual.getName());

    }

    @Test(expected = CustomerNotFoundExpection.class)
    public void findByNumberIdentification_when_not_found() {

        when(customerRepository.findByNumerIdentification("9297638", "CC")).thenReturn(null);
        customerService.findByNumberIdentification("9297638", "CC");

    }

    @Test
    public void updateCustomer() {

        CustomerPSTDtoRq customerPSTDtoRq = serviceCustomerMockGetByIdentification();

        Customer customer = ConvertFromCustomerPSTDtoRqtoCustomer(customerPSTDtoRq);

        when(customerRepository.existsById(1L)).thenReturn(true);

        new MockUp<Mapper>() {
            @mockit.Mock
            public Customer mapperRequest(CustomerPSTDtoRq customerPSTDtoRq) {
                return customer;
            }
        };
        Deencapsulation.invoke(Mapper.class, "mapperRequest", customerPSTDtoRq);
        when(customerRepository.save(customer)).thenReturn(customer);

        CustomerPSTDtoRs actual = customerService.updateCustomer(customerPSTDtoRq);

        assertEquals(customer.getCustomerId(), actual.getCustomerId());
    }

    @Test(expected = CustomerNotFoundExpection.class)
    public void updateCustomer_when_Not_found_customer() {

        CustomerPSTDtoRq customerPSTDtoRq = serviceCustomerMockGetByIdentification();
        when(customerRepository.existsById(1L)).thenReturn(false);
        customerService.updateCustomer(customerPSTDtoRq);

    }

    @Test
    public void deleteCustomer() {
        when(customerRepository.existsById(1L)).thenReturn(true);
        Message message = customerService.deleteCustomer(1L);

        assertEquals("Se elimino customer: id: 1", message.getMessage());
    }

    @Test(expected = CustomerNotFoundExpection.class)
    public void deleteCustomer_when_not_found_customer() {
        when(customerRepository.existsById(1L)).thenReturn(false);
        customerService.deleteCustomer(1L);
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

        Optional<Customer> optional = Optional.of(customer);
        return optional;
    }

    public Customer ConvertFromCustomerPSTDtoRqtoCustomer(CustomerPSTDtoRq customerPSTDtoRq) {

        Customer customer = new Customer();

        Identification identification = new Identification();

        IdentificationType identificationType = new IdentificationType();

        identification.setId(customerPSTDtoRq.getIdentification().getId());
        identification.setNumberIdentification(customerPSTDtoRq.getIdentification().getNumberIdentification());
        identification.setExpeditionDate(customerPSTDtoRq.getIdentification().getExpeditionDate());
        identification.setExpeditionCity(customerPSTDtoRq.getIdentification().getExpeditionCity());
        identificationType.setCode(customerPSTDtoRq.getIdentification().getIdentificationType().getCode());
        identification.setIdentificationType(identificationType);

        customer.setCustomerId(customerPSTDtoRq.getCustomerId());
        customer.setIdentification(identification);
        customer.setName(customerPSTDtoRq.getName());
        customer.setLastName(customerPSTDtoRq.getLastName());
        customer.setBirthDate(customerPSTDtoRq.getBirthDate());
        customer.setBirthCity(customerPSTDtoRq.getBirthCity());
        customer.setCountry(customerPSTDtoRq.getCountry());
        customer.setMaritalStatus(customerPSTDtoRq.getMaritalStatus());
        customer.setProfession(customerPSTDtoRq.getProfession());
        Gender gender = new Gender();
        gender.setCode(customerPSTDtoRq.getGender().getCode());
        customer.setGender(gender);

        return customer;
    }


    public CustomerGETDtoRs getCustomerGETDtoRs(CustomerPSTDtoRq customerPSTDtoRq) {

        CustomerGETDtoRs customer = new CustomerGETDtoRs();

        IdentificationDto identification = new IdentificationDto();

        IdentificationTypeDto identificationType = new IdentificationTypeDto();

        identification.setId(customerPSTDtoRq.getIdentification().getId());
        identification.setNumberIdentification(customerPSTDtoRq.getIdentification().getNumberIdentification());
        identification.setExpeditionDate(customerPSTDtoRq.getIdentification().getExpeditionDate());
        identification.setExpeditionCity(customerPSTDtoRq.getIdentification().getExpeditionCity());
        identificationType.setCode(customerPSTDtoRq.getIdentification().getIdentificationType().getCode());
        identification.setIdentificationType(identificationType);

        customer.setCustomerId(customerPSTDtoRq.getCustomerId());
        customer.setIdentification(identification);
        customer.setName(customerPSTDtoRq.getName());
        customer.setLastName(customerPSTDtoRq.getLastName());
        customer.setBirthDate(customerPSTDtoRq.getBirthDate());
        customer.setBirthCity(customerPSTDtoRq.getBirthCity());
        customer.setCountry(customerPSTDtoRq.getCountry());
        customer.setMaritalStatus(customerPSTDtoRq.getMaritalStatus());
        customer.setProfession(customerPSTDtoRq.getProfession());

        GenderDto gender = new GenderDto();
        gender.setCode(customerPSTDtoRq.getGender().getCode());
        customer.setGender(gender);

        return customer;
    }

    public CustomerPSTDtoRq serviceCustomerMockGetByIdentification() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        CustomerPSTDtoRq customer = new CustomerPSTDtoRq();
        customer.setCustomerId(1L);
        customer.setName("Leehener");
        customer.setLastName("Cabeza");
        customer.setBirthCity("Cartagena");
        customer.setCountry("Colombia");
        customer.setMaritalStatus("Soltero");
        customer.setProfession("System Engineer");
        customer.setBirthDate(LocalDate.parse("1982-04-24", formatter));

        GenderDto gender = new GenderDto();
        gender.setCode("M");
        gender.setDescription("Masculino");
        customer.setGender(gender);

        IdentificationTypeDto identificationType = new IdentificationTypeDto();
        identificationType.setCode("CC");
        identificationType.setIdentificationType("Cedula de Ciudadania");
        IdentificationDto identification = new IdentificationDto();
        identification.setIdentificationType(identificationType);
        identification.setId(1L);
        identification.setNumberIdentification("9297638");
        identification.setExpeditionCity("Bolivar");
        identification.setExpeditionDate(LocalDate.parse("2000-11-20", formatter));
        customer.setIdentification(identification);

        return customer;
    }

    public Customer getCustomerMock() {

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