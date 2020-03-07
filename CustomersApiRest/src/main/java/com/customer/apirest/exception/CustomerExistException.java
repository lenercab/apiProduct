package com.customer.apirest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class CustomerExistException extends RuntimeException{

    public CustomerExistException(String message){
        super(message);
    }
}
