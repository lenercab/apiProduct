package com.customer.apirest.controller;

import com.customer.apirest.model.Customer;
import com.customer.apirest.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.*;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

@RestController
public class CustomersController {

    Logger logger = LoggerFactory.getLogger(CustomersController.class);

    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "Add  customer", response = Customer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create customer was successfully"),
            @ApiResponse(code = 400, message = "Bab request"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found "),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 500, message = "Error internal server")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/Customers")
    public ResponseEntity<Object> CustomersPST(@Valid @RequestBody Customer customer,
                                               @RequestHeader(name = "x-terminal", required = true) String xTerminal) {

        logger.info("get to started Controller CustomerPST");
        HttpHeaders responseHeaders = new HttpHeaders();

        customerService.insertCustomer(customer);

        responseHeaders.set("date-operation", new Date().toString());
        responseHeaders.set("x-terminal", xTerminal);

        Hateoas.getHateoasCustomerPST(customer);

        return ResponseEntity.status(201).headers(responseHeaders).body(customer);
    }

    @ApiOperation(value = "Consult customer by id", response = Customer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bab request"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found "),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 500, message = "Error internal server")
    })
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/Customers/{id}")
    public ResponseEntity<Object> CustomersGETById(@PathVariable("id") Long id,
                                                   @RequestHeader(name = "x-terminal", required = true) String xTerminal) {
        logger.info("get to started Controller CustomersGETById");
        HttpHeaders responseHeaders = new HttpHeaders();
        Optional customer = customerService.findById(id);
        responseHeaders.set("date-operation", new Date().toString());
        responseHeaders.set("x-terminal", xTerminal);
        return ResponseEntity.ok().headers(responseHeaders).body(customer);
    }

    @ApiOperation(value = "Consult customer by identificationType and numberIdentification", response = Customer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bab request"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found "),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 500, message = "Error internal server")
    })
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/Customers/{identificationType}/{numberIdentification}")
    public ResponseEntity<Object> CustomerGetByNumberIdentification(
            @PathVariable("numberIdentification") String numberIdentification,
            @PathVariable("identificationType") String identificationType) {
        logger.info("get to started Controller CustomerGetByNumberIdentification");
        return ResponseEntity.ok()
                .body(customerService.findByNumberIdentification(numberIdentification, identificationType));
    }

    @ApiOperation(value = "Consult customer by identificationType and numberIdentification")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Delete operation was successfully"),
            @ApiResponse(code = 400, message = "Bab request"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found "),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 500, message = "Error internal server")
    })
    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/Customers/{id}")
    public ResponseEntity<Object> CustomersDeleteById(@PathVariable("id") Long id,
                                                      @RequestHeader(name = "x-terminal", required = true) String xTerminal) {
        logger.info("get to started Controller CustomersDeleteById");
        HttpHeaders responseHeaders = new HttpHeaders();
        String message = customerService.deleteCustomer(id);
        responseHeaders.set("date-operation", new Date().toString());
        responseHeaders.set("x-terminal", xTerminal);
        return ResponseEntity.ok().headers(responseHeaders).body(message);
    }

    @ApiOperation(value = "Modificate the information of customer", response = Customer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "update customer was successfully"),
            @ApiResponse(code = 400, message = "Bab request"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found "),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 500, message = "Error internal server")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PatchMapping("/Customers")
    public ResponseEntity<Object> CustomerPTC(@Valid @RequestBody Customer customer,
                                              @RequestHeader(name = "x-terminal", required = true) String xTerminal) {
        logger.info("get to started Controller CustomersDeleteById");
        HttpHeaders responseHeaders = new HttpHeaders();
        customerService.updateCustomer(customer);
        Hateoas.getHateoasCustomerPTC(customer);
        responseHeaders.set("date-operation", new Date().toString());
        responseHeaders.set("x-terminal", xTerminal);
        return ResponseEntity.status(201).body(customer);
    }
}
