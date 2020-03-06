package com.customer.apirest.service;

import com.customer.apirest.controller.CustomersController;
import com.customer.apirest.exception.CustomerNotFoundExpection;
import com.customer.apirest.model.Customer;
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
        return customerRepository.save(customer);
    }

    public Optional findById(Long id) {
        logger.info("get to started findById operation");
        Optional optional = customerRepository.findById(id);
        if (optional == null)
            throw new CustomerNotFoundExpection("Not found customer");

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
        return customerRepository.save(customer);
    }

    public String deleteCustomer(long id) {
        logger.info("get to started deleteCustomer operation");
        customerRepository.deleteById(id);
        return "Se elimino customer";
    }
}
