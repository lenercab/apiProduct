package com.address.apirest.controller;

import com.address.apirest.dto.*;
import com.address.apirest.model.Address;
import com.address.apirest.service.AddressService;
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
public class AddressController {

    Logger logger = LoggerFactory.getLogger(AddressController.class);
    @Autowired
    AddressService addressService;

    @ApiOperation(value = "Add address", response = AddressPSTrs.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Address was created successfully"),
            @ApiResponse(code = 400, message = "Bab request"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found "),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 500, message = "Error internal server")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/Addresses")
    public ResponseEntity<Object> AddressPST(@Valid @RequestBody AddressPSTrq addressPSTrq) {
        logger.info("get to stared controller layer create address");
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.insertAddress(addressPSTrq));
    }

    @ApiOperation(value = "Modify  address", response = AddressPTCrs.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Address was modified successfully"),
            @ApiResponse(code = 400, message = "Bab request"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found "),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 500, message = "Error internal server")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PatchMapping("/Addresses")
    public ResponseEntity<Object> AddressPTC(@Valid @RequestBody AddressPTCrq addressPTCrq) {
        logger.info("get to stared controller layer modify address");
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.updateAddress(addressPTCrq));
    }

    @ApiOperation(value = "Consult list of addresses", response = AddressGETALLrs.class)
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
    @GetMapping("/Customer/{customerId}/Addresses")
    public ResponseEntity<Object> getAllAddressesByCustomerId(@PathVariable("customerId") long customerId) {
        logger.info("get to stared controller layer get all addresses of the customer");
        return ResponseEntity.status(HttpStatus.OK).body(addressService.getAllAddressByCustomerId(customerId));
    }

    @ApiOperation(value = "Consult address", response = AddressGETrs.class)
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
    @GetMapping("/Customer/{customerId}/Addresses/{id}")
    public ResponseEntity<Object> getAddressByIdAndCustomerId(@PathVariable("id") long id, @PathVariable("customerId") long customerId) {
        logger.info("get to stared controller layer get all addresses of the customer");
        return ResponseEntity.status(HttpStatus.OK).body(addressService.getAddressByIdAndCustomerId(id, customerId));
    }

    @ApiOperation(value = "Delete Address by id", response = Message.class)
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
    @DeleteMapping("/Customer/{customerId}/Addresses/{id}")
    public ResponseEntity<Object> deleteAddressById(@PathVariable("id") long id, @PathVariable("customerId") long customerId) {
        logger.info("get to stared controller layer delete address by id");
        return ResponseEntity.status(HttpStatus.OK).body(addressService.deleteAdressyId(id, customerId));
    }
}
