package com.customer.apirest.exception;

import com.customer.apirest.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import org.hibernate.exception.ConstraintViolationException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
public class CustomerResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

        Logger logger = LoggerFactory.getLogger(CustomerService.class);

        @ExceptionHandler(DateTimeParseException.class)
        public final ResponseEntity<Object> handlerAllDateTimeParseException(DateTimeParseException ex, WebRequest request) {

                logger.info(ex.getMessage());
                logger.error(ex.toString());

                ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                                request.getDescription(false));
                return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);

        }

        @ExceptionHandler(CustomerNotFoundExpection.class)
        public final ResponseEntity<Object> handlerAllCustomerNotFoundExpection(Exception ex, WebRequest request) {

                logger.info(ex.getMessage());
                logger.error(ex.toString());
                ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                                request.getDescription(false));
                return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);

        }

        @ExceptionHandler(CustomerExistException.class)
        public final ResponseEntity<Object> handlerAllCustomerExistException(Exception ex, WebRequest request) {

                logger.info(ex.getMessage());
                logger.error(ex.toString());
                ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                        request.getDescription(false));
                return new ResponseEntity<Object>(exceptionResponse, HttpStatus.FORBIDDEN);

        }

        @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
        public final ResponseEntity<Object> handlerAllSQLIntegrityConstraintViolationException(Exception ex,
                        WebRequest request) {

                logger.info(ex.getMessage());
                logger.error(ex.toString());
                ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                                request.getDescription(false));
                return new ResponseEntity<Object>(exceptionResponse, HttpStatus.CONFLICT);

        }

        @ExceptionHandler(javax.validation.ConstraintViolationException.class)
        public final ResponseEntity<Object> handlerAllConstraintViolationException(
                        javax.validation.ConstraintViolationException ex, WebRequest request) {
                Map<String, String> errors = new HashMap<>();
                ex.getConstraintViolations().forEach(x -> {
                        errors.put("error", x.getMessageTemplate());
                });
                ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), errors.get("error"),
                                request.getDescription(false));
                return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);

        }

        @ExceptionHandler({ DataIntegrityViolationException.class })
        public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
                int errorCode = ex.getErrorCode();

                logger.info(ex.getMessage());
                logger.error(ex.toString());

                ExceptionResponse exceptionResponse1 = new ExceptionResponse(new Date(), ex.getMessage(),
                                request.getDescription(true));
                return new ResponseEntity<Object>(exceptionResponse1, HttpStatus.BAD_REQUEST);

        }

        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                        HttpHeaders headers, HttpStatus status, WebRequest request) {
                ObjectError objectError = ex.getBindingResult().getAllErrors().get(0);

                logger.info(objectError.getDefaultMessage());
                logger.error(ex.toString());

                ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), objectError.getDefaultMessage(),
                                request.getDescription(false));
                return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
        }

        @Override
        protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                        HttpHeaders headers, HttpStatus status, WebRequest request) {

                logger.info(ex.getMessage());
                logger.error(ex.toString());
                ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                                request.getDescription(false));
                return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
        }

        @Override
        protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                        HttpHeaders headers, HttpStatus status, WebRequest request) {

                logger.info(ex.getMessage());
                logger.error(ex.toString());
                ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                                request.getDescription(false));
                return new ResponseEntity<Object>(exceptionResponse, HttpStatus.METHOD_NOT_ALLOWED);
        }

        @Override
        protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                        HttpHeaders headers, HttpStatus status, WebRequest request) {

                logger.info(ex.getMessage());
                logger.error(ex.toString());
                ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                                request.getDescription(false));
                return new ResponseEntity<Object>(exceptionResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }

}
