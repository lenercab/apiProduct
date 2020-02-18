package com.customer.apirest.controller;

import com.customer.apirest.model.Customer;
import com.customer.apirest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
public class CustomersController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/Customers")
    public ResponseEntity<Object> CustomersPST(@RequestBody Customer customer,
            @RequestHeader(name = "x-terminal", required = true) String xTerminal) {

        HttpHeaders responseHeaders = new HttpHeaders();

        customerService.insertCustomer(customer);
        responseHeaders.set("date-operation", new Date().toString());
        responseHeaders.set("x-terminal", xTerminal);

        Hateoas hateoas = new Hateoas();
        hateoas.getHateoasCustomer(customer);
        return ResponseEntity.status(201).headers(responseHeaders).body(customer);
    }

    @GetMapping("/Customers/{id}")
    public ResponseEntity<Object> CustomersGETById(@PathVariable("id") Long id,
            @RequestHeader(name = "x-terminal", required = true) String xTerminal) {

        HttpHeaders responseHeaders = new HttpHeaders();
        Optional customer = customerService.findById(id);
        responseHeaders.set("date-operation", new Date().toString());
        responseHeaders.set("x-terminal", xTerminal);
        return ResponseEntity.ok().headers(responseHeaders).body(customer);
    }

    @GetMapping("/Customers/{identificationType}/{numberIdentification}")
    public ResponseEntity<Object> CustomerGetByNumberIdentification(
            @PathVariable("numberIdentification") String numberIdentification, @PathVariable("identificationType") String identificationType) {
        return ResponseEntity.ok().body(customerService.findByNumberIdentification(numberIdentification, identificationType));
    }
    @DeleteMapping("/Customers/{id}")
    public ResponseEntity<Object> CustomersDeleteById(@PathVariable("id") Long id,
            @RequestHeader(name = "x-terminal", required = true) String xTerminal) {

        HttpHeaders responseHeaders = new HttpHeaders();
        String message  = customerService.deleteCustomer(id);
        responseHeaders.set("date-operation", new Date().toString());
        responseHeaders.set("x-terminal", xTerminal);
        return ResponseEntity.ok().headers(responseHeaders).body(message);
    }

    @PatchMapping("/Customers")
    public ResponseEntity<Object> CustomerPTC(@RequestBody Customer customer) {
        customerService.updateCustomer(customer);
        Hateoas hateoas = new Hateoas();
        hateoas.getHateoasCustomer(customer);
        return ResponseEntity.status(201).body(customer);
    }
}
