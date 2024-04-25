package com.example.demo.dto;

import java.time.LocalDateTime;
public record TransactionDto(Long id, Long accountId,String transactionType, Double amount, LocalDateTime timestamp ) {
    
}
