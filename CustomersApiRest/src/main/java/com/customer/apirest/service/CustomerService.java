package com.customer.apirest.service;

import com.customer.apirest.exception.CustomerNotFoundExpection;
import com.customer.apirest.model.Customer;
import com.customer.apirest.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer insertCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional findById(Long id) {
        Optional optional = customerRepository.findById(id);
        if (optional.isEmpty())
            throw new CustomerNotFoundExpection("Not found customer");

        return optional;
    }

    public Customer findByNumberIdentification(String numberIdentification, String identificationType) {
        Customer customer = customerRepository.findByNumerIdentification(numberIdentification, identificationType);
        if (customer == null) {
            throw new CustomerNotFoundExpection("Not found customer");
        }
        return customer;
    }

    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public String deleteCustomer(long id) {
        customerRepository.deleteById(id);
        return "Se elimino customer";
    }
}
