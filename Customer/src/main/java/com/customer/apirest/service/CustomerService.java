package com.customer.apirest.service;

import com.customer.apirest.dto.CustomerGETDtoRs;
import com.customer.apirest.dto.CustomerPSTDtoRq;
import com.customer.apirest.dto.CustomerPSTDtoRs;
import com.customer.apirest.dto.Message;
import com.customer.apirest.exception.CustomerExistException;
import com.customer.apirest.exception.CustomerNotFoundExpection;
import com.customer.apirest.model.*;
import com.customer.apirest.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.customer.apirest.util.Mapper.mapperRequest;


@Service
public class CustomerService {

    Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;


    public CustomerPSTDtoRs  insertCustomer(CustomerPSTDtoRq customerPSTDtoRq) {
        logger.info("get to started insertCustomer operation");

        if (customerRepository.existIdentification(customerPSTDtoRq.getIdentification().getNumberIdentification(),
            customerPSTDtoRq.getIdentification().getIdentificationType().getCode()) == 0) {

            Customer customer = mapperRequest(customerPSTDtoRq);
            customerRepository.save(customer);

            CustomerPSTDtoRs customerPSTDtoRs  = new CustomerPSTDtoRs();
            customerPSTDtoRq.setCustomerId(customer.getCustomerId());
            customerPSTDtoRq.getIdentification().setId(customer.getIdentification().getId());

            customerPSTDtoRs.setCustomerId(customer.getCustomerId());

            return  customerPSTDtoRs;
        }
        throw new CustomerExistException("Already exists customer");
    }

    public CustomerGETDtoRs findById(Long id) {
        logger.info("get to started findById operation");
        if (!customerRepository.existsById(id))
            throw new CustomerNotFoundExpection("Not found customer");

        Customer customer = customerRepository.findByCustomerId(id);
        ModelMapper modelMapper = new ModelMapper();
        CustomerGETDtoRs customerGETDtoRs = modelMapper.map(customer, CustomerGETDtoRs.class);
        return customerGETDtoRs;
    }

    public CustomerGETDtoRs findByNumberIdentification(String numberIdentification, String identificationType) {
        logger.info("get to started findByNumberIdentification operation");
        ModelMapper modelMapper = new ModelMapper();
        Customer customer = customerRepository.findByNumerIdentification(numberIdentification, identificationType);

        if (customer == null) {
            throw new CustomerNotFoundExpection("Not found customer");
        }

        CustomerGETDtoRs customerGETDtoRs = modelMapper.map(customer, CustomerGETDtoRs.class);
        return customerGETDtoRs;
    }

    public CustomerPSTDtoRs updateCustomer(CustomerPSTDtoRq customerPSTDtoRq) {
        logger.info("get to started updateCustomer operation");
        if (!customerRepository.existsById(customerPSTDtoRq.getCustomerId())) {
            throw new CustomerNotFoundExpection("Not found customer");
        }
        Customer customer = mapperRequest(customerPSTDtoRq);
        customer.setCustomerId(customerPSTDtoRq.getCustomerId());
        customer.getIdentification().setId(customerPSTDtoRq.getIdentification().getId());
        customerRepository.save(customer);
        CustomerPSTDtoRs customerPSTDtoRs  = new CustomerPSTDtoRs();
        customerPSTDtoRq.setCustomerId(customer.getCustomerId());
        customerPSTDtoRq.getIdentification().setId(customer.getIdentification().getId());

        customerPSTDtoRs.setCustomerId(customer.getCustomerId());

        return customerPSTDtoRs;
    }

    public Message deleteCustomer(long id) {
        logger.info("get to started deleteCustomer operation");

        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundExpection("Not found customer: id:" + id);
        }
        customerRepository.deleteById(id);
        Message message = new Message();
        message.setMessage("Se elimino customer: id: " + id);
        return message;
    }

}
