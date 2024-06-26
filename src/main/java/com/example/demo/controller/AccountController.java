package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.dto.AccountDto;
import com.example.demo.dto.TransactionDto;
import com.example.demo.dto.TransactionFoundDto;
import com.example.demo.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
// test api:-http://localhost:8080/api/account
// {
//     "id": "1123",
//     "accountName": "rushabh",
//     "accountType": "Saving",
//     "balance": "10000"
// }
@RequestMapping("/api/account")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        AccountDto savedAccount = accountService.createAccountDto(accountDto);
        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }
// test api:- http://localhost:8080/api/account/9
    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable Long accountId) {
        AccountDto account = accountService.getAccountDto(accountId);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
// test api:- http://localhost:8080/api/account/9/deposit?amount=400
    @PutMapping("/{accountId}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long accountId, @RequestParam Double amount) {
        AccountDto account = accountService.deposit(accountId, amount);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

// test api:- http://localhost:8080/api/account/9/withdraw?amount=400
    @PutMapping("/{accountId}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long accountId, @RequestParam Double amount) {
        AccountDto account = accountService.withdraw(accountId, amount);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

// test api:-http://localhost:8080/api/account/remittance
    @PostMapping("/remittance")
    public ResponseEntity<Void> remittance(@RequestBody TransactionFoundDto transactionFoundDto) {
        accountService.remittance(transactionFoundDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
// test api:- http://localhost:8080/api/account/9/transactions
    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<TransactionDto>> getTransactions(@PathVariable Long accountId) {
        List<TransactionDto> transactions = accountService.getTransactions(accountId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

// test api:- http://localhost:8080/api/account/9
    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
