package com.example.demo.exception;

import java.time.LocalDateTime;
public record Error(LocalDateTime timestamp, String message, String details,String errorCode) {
}
