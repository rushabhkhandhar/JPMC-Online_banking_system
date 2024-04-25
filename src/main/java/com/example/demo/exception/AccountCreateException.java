package com.example.demo.exception;

public class AccountCreateException extends RuntimeException{
    public AccountCreateException(String message) {
        super(message);
    }
}
