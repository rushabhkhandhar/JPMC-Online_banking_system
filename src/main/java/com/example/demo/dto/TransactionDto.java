package com.example.demo.dto;

public record TransactionDto(Long id, String transactionType, Double amount, String transactionDate, String transactionTime, Long accountId) {
    
}
