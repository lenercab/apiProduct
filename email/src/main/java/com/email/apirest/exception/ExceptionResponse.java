package com.email.apirest.exception;

import java.util.Date;

public class ExceptionResponse {

    private Date timestap;
    private String message;
    private String details;

    public ExceptionResponse() {
    }

    public ExceptionResponse(Date timestap, String message, String details) {
        this.timestap = timestap;
        this.message = message;
        this.details = details;
    }

    public Date getTimestap() {
        return timestap;
    }

    public void setTimestap(Date timestap) {
        this.timestap = timestap;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
