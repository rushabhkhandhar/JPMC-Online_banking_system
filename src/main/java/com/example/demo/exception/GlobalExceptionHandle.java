package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(AccountException.class)
    public ResponseEntity<Error> handleAccountException(AccountException ex, WebRequest request) {
        Error error = new Error(LocalDateTime.now(), ex.getMessage(), request.getDescription(false),
                "ACCOUNT_CREATION_ERROR");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountCreateException.class)
    public ResponseEntity<Error> handleAccountCreateException(AccountCreateException ex, WebRequest request) {
        Error error = new Error(LocalDateTime.now(), ex.getMessage(), request.getDescription(false),
                "ACCOUNT_CREATION_ERROR");
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(AmountException.class)
    public ResponseEntity<Error> handleAllExceptions(Exception ex, WebRequest request) {
        Error error = new Error(LocalDateTime.now(), ex.getMessage(), request.getDescription(false),
                "INSUFFIECIENT_BALANCE_ERROR");
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleAllGeneralExceptions(Exception ex, WebRequest request) {
        Error error = new Error(LocalDateTime.now(), ex.getMessage(), request.getDescription(false),
                "INTERNAL_SERVER_ERROR");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
