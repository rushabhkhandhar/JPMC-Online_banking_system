package com.example.demo.service;

import com.example.demo.dto.AccountDto;
import com.example.demo.dto.TransactionDto;
import com.example.demo.dto.TransactionFoundDto;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.entity.Account;
import com.example.demo.entity.Transaction;
import com.example.demo.exception.AccountCreateException;
import com.example.demo.exception.AccountException;
import com.example.demo.exception.AmountException;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;
import java.util.stream.Collectors;


@Service
public class AccountMainService implements AccountService {
    private  AccountRepository accountRepository;
    private  TransactionRepository transactionRepository;
    
    private static final String TRANSACTION_TYPE_DEPOSIT = "DEPOSIT";
    private static final String TRANSACTION_TYPE_WITHDRAW = "WITHDRAW";
    private static final String TRANSACTION_TYPE_REMITTANCE = "REMITTANCE";
    

    public AccountMainService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public AccountDto createAccountDto(AccountDto accountDto) {
        return null;
        }
   
    @Override
    public AccountDto getAccountDto(Long accountId) {
        return null;
    }

    @Override
    public AccountDto deposit(Long accountId, Double amount) {
        return null;
    }

    @Override
    public AccountDto withdraw(Long accountId, Double amount) {
        return null;
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        return null;
    }

    @Override
    public void deleteAccount(Long accountId) {
    }

    @Override
    public void remittance(TransactionFoundDto transactionFoundDto) {
    }

    @Override
    public List<TransactionDto> getTransactions(Long accountId) {
        return null;
    }

   private TransactionDto convertEntityToDto(Transaction transaction)
   {
    return null;
   }
   
}

