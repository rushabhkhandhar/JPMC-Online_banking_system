package com.example.demo.service;

import com.example.demo.dto.AccountDto;
import com.example.demo.dto.TransactionDto;
import com.example.demo.dto.TransactionFoundDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccountDto(AccountDto accountDto);

    AccountDto getAccountDto(Long accountId);

    // AccountDto updateAccountDto(Long accountId, AccountDto accountDto);

    AccountDto deposit(Long accountId, Double amount);

    AccountDto withdraw(Long accountId, Double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long accountId);

    void remittance(TransactionFoundDto transactionFoundDt);

    List<TransactionDto> getTransactions(Long accountId);

}
