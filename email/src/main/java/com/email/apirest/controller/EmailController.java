package com.email.apirest.controller;

import com.email.apirest.dto.*;
import com.email.apirest.service.EmailService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class EmailController {

    Logger logger = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private EmailService emailService;

    @ApiOperation(value = "Consult all emails of one customer", response = EmailGETALL.class)
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
    @GetMapping("/Customers/{customerId}/Emails")
    public ResponseEntity<Object> getAllEmails(@PathVariable("customerId") long customerId) {
        logger.info("get to started controller getAllEmails");
        return ResponseEntity.ok(emailService.getAllEmails(customerId));
    }

    @ApiOperation(value = "Consult Email by id", response = EmailGETById.class)
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
    @GetMapping("/Customers/{customerId}/Emails/{id}")
    public ResponseEntity<Object> getByIdEmail(@PathVariable("customerId") long customerId, @PathVariable("id") long id) {
        logger.info("get to started controller getByIdEmail");
        return ResponseEntity.ok(emailService.getByIdEmail(id, customerId));
    }

    @ApiOperation(value = "Add  email", response = EmailPSTRs.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create Email was successfully"),
            @ApiResponse(code = 400, message = "Bab request"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found "),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 500, message = "Error internal server")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/Emails")
    public ResponseEntity<Object> emailPST(@Valid @RequestBody EmailPSTRq emailPSTRq) {
        logger.info("get to started controller emailPST");
        return ResponseEntity.status(HttpStatus.CREATED).body(emailService.saveEmail(emailPSTRq));
    }

    @ApiOperation(value = "Modificate the information of Email", response = EmailPTCRs.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "update Email was successfully"),
            @ApiResponse(code = 400, message = "Bab request"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found "),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 500, message = "Error internal server")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PatchMapping("/Emails")
    public ResponseEntity<Object> emailPTC(@Valid @RequestBody EmailPTCRq emailPTCRq) {
        logger.info("get to started controller emailPTC");
        return ResponseEntity.status(HttpStatus.CREATED).body(emailService.updateEmail(emailPTCRq));
    }

    @ApiOperation(value = "Delete Email by id", response = Message.class)
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
    @DeleteMapping("/Customers/{customerId}/Emails/{id}")
    public ResponseEntity<Object> emailDeleteById(@PathVariable("id") long id, @PathVariable("customerId") long customerId) {
        logger.info("get to started controller emailDeleteById");
        return ResponseEntity.ok().body(emailService.deleteEmailById(id, customerId));
    }
}
