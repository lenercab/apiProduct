package com.customer.apirest.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundExpection extends RuntimeException{ 

    public CustomerNotFoundExpection(String message){
        super(message);
    }
 }