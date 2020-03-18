package com.customer.apirest.controller;

import com.customer.apirest.dto.CustomerGETDtoRs;
import com.customer.apirest.dto.CustomerPSTDtoRq;
import com.customer.apirest.dto.CustomerPSTDtoRs;
import com.customer.apirest.dto.Message;
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

import javax.validation.Valid;

@RestController
public class CustomersController {

    Logger logger = LoggerFactory.getLogger(CustomersController.class);

    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "Add  customer", response = CustomerPSTDtoRs.class)
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
    public ResponseEntity<Object> CustomersPST(@Valid @RequestBody CustomerPSTDtoRq customerPSTDtoRq,
                                               @RequestHeader(name = "x-terminal", required = true) String xTerminal) {

        logger.info("get to started Controller CustomerPST");
        HttpHeaders responseHeaders = new HttpHeaders();
        CustomerPSTDtoRs customerPSTDtoRs = customerService.insertCustomer(customerPSTDtoRq);
        responseHeaders.set("date-operation", new Date().toString());
        responseHeaders.set("x-terminal", xTerminal);
        Hateoas.getHateoasCustomerPST(customerPSTDtoRs, customerPSTDtoRq);

        return ResponseEntity.status(201).headers(responseHeaders).body(customerPSTDtoRs);
    }

    @ApiOperation(value = "Consult customer by id", response = CustomerGETDtoRs.class)
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
        CustomerGETDtoRs customerGETDtoRs = customerService.findById(id);
        responseHeaders.set("date-operation", new Date().toString());
        responseHeaders.set("x-terminal", xTerminal);
        return ResponseEntity.ok().headers(responseHeaders).body(customerGETDtoRs);
    }

    @ApiOperation(value = "Consult customer by identificationType and numberIdentification", response = CustomerGETDtoRs.class)
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

    @ApiOperation(value = "Consult customer by identificationType and numberIdentification", response = Message.class)
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
        responseHeaders.set("date-operation", new Date().toString());
        responseHeaders.set("x-terminal", xTerminal);
        return ResponseEntity.ok().headers(responseHeaders).body(customerService.deleteCustomer(id));
    }

    @ApiOperation(value = "Modificate the information of customer", response = CustomerPSTDtoRs.class)
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
    public ResponseEntity<Object> CustomerPTC(@Valid @RequestBody CustomerPSTDtoRq customerPSTDtoRq,
                                              @RequestHeader(name = "x-terminal", required = true) String xTerminal) {
        logger.info("get to started Controller CustomersDeleteById");
        HttpHeaders responseHeaders = new HttpHeaders();
        CustomerPSTDtoRs customerPSTDtoRs = customerService.updateCustomer(customerPSTDtoRq);
        Hateoas.getHateoasCustomerPTC(customerPSTDtoRs, customerPSTDtoRq);
        responseHeaders.set("date-operation", new Date().toString());
        responseHeaders.set("x-terminal", xTerminal);

        return ResponseEntity.status(201).body(customerPSTDtoRs);
    }
}
