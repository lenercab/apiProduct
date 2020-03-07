package com.customer.apirest.service;

import com.customer.apirest.controller.CustomersController;
import com.customer.apirest.exception.CustomerExistException;
import com.customer.apirest.exception.CustomerNotFoundExpection;
import com.customer.apirest.model.Customer;
import com.customer.apirest.model.Message;
import com.customer.apirest.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    public Customer insertCustomer(Customer customer) {
        logger.info("get to started insertCustomer operation");
        if (customerRepository.existIdentification(customer.getIdentification().getNumberIdentification(),
                customer.getIdentification().getIdentificationType().getCode()) == 0) {
            return customerRepository.save(customer);
        }
        throw new CustomerExistException("Already exists customer");
    }

    public Optional findById(Long id) {
        logger.info("get to started findById operation");
        if (!customerRepository.existsById(id))
            throw new CustomerNotFoundExpection("Not found customer");

        Optional optional = customerRepository.findById(id);

        return optional;
    }

    public Customer findByNumberIdentification(String numberIdentification, String identificationType) {
        logger.info("get to started findByNumberIdentification operation");
        Customer customer = customerRepository.findByNumerIdentification(numberIdentification, identificationType);
        if (customer == null) {
            throw new CustomerNotFoundExpection("Not found customer");
        }
        return customer;
    }

    public Customer updateCustomer(Customer customer) {
        logger.info("get to started updateCustomer operation");
        if (!customerRepository.existsById(customer.getCustomerId())) {
            throw new CustomerNotFoundExpection("Not found customer");
        }
        return customerRepository.save(customer);
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
