package com.example.demo.dto;

public record TransactionFoundDto(long fromAccountId, long toAccountId, double amount, String transactionDate, String transactionTime) {
    
}