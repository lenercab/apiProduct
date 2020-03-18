package com.phone.phone.controller;

import com.phone.phone.dto.*;
import com.phone.phone.model.Phone;
import com.phone.phone.service.PhoneService;
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
public class PhoneController {

    Logger logger = LoggerFactory.getLogger(PhoneController.class);
    @Autowired
    PhoneService phoneService;

    @ApiOperation(value = "Add email", response = PhonePSTrs.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Phone was created successfully"),
            @ApiResponse(code = 400, message = "Bab request"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found "),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 500, message = "Error internal server")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/Phones")
    public ResponseEntity<Object> PhonePST(@Valid @RequestBody PhonePSTrq phonePSTrq) {
        logger.info("get to stared controller layer create phone");
        return ResponseEntity.status(HttpStatus.CREATED).body(phoneService.insertPhone(phonePSTrq));
    }

    @ApiOperation(value = "Modify  phone", response = PhonePTCrs.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Phone was modified successfully"),
            @ApiResponse(code = 400, message = "Bab request"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found "),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 500, message = "Error internal server")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PatchMapping("/Phones")
    public ResponseEntity<Object> PhonePTC(@Valid @RequestBody PhonePTCrq phonePTCrq) {
        logger.info("get to stared controller layer modify phone");
        return ResponseEntity.status(HttpStatus.CREATED).body(phoneService.updatePhone(phonePTCrq));
    }

    @ApiOperation(value = "Consult list of phones", response = PhonesGETALLrs.class)
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
    @GetMapping("/Customer/{customerId}/Phones")
    public ResponseEntity<Object> getAllPhonesByCustomerId(@PathVariable("customerId") long customerId) {
        logger.info("get to stared controller layer get all phones of the customer");
        return ResponseEntity.status(HttpStatus.OK).body(phoneService.getAllPhoneByCustomerId(customerId));
    }

    @ApiOperation(value = "Consult phones", response = PhoneGETrs.class)
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
    @GetMapping("/Customer/{customerId}/Phones/{id}")
    public ResponseEntity<Object> getPhonesByIdAndCustomerId(@PathVariable("id") long id, @PathVariable("customerId") long customerId) {
        logger.info("get to stared controller layer get all phones of the customer");
        return ResponseEntity.status(HttpStatus.OK).body(phoneService.getPhoneByIdAndCustomerId(id, customerId));
    }

    @ApiOperation(value = "Delete phone by id", response = Message.class)
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
    @DeleteMapping("/Customer/{customerId}/Phones/{id}")
    public ResponseEntity<Object> deletePhonesById(@PathVariable("id") long id, @PathVariable("customerId") long customerId) {
        logger.info("get to stared controller layer delete phones by id");
        return ResponseEntity.status(HttpStatus.OK).body(phoneService.deletePhoneById(id, customerId));
    }
}
