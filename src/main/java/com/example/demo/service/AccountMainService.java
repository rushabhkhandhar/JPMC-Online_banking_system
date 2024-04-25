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
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class AccountMainService implements AccountService {
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    private static final String TRANSACTION_TYPE_DEPOSIT = "DEPOSIT";
    private static final String TRANSACTION_TYPE_WITHDRAW = "WITHDRAW";
    private static final String TRANSACTION_TYPE_REMITTANCE = "REMITTANCE";

    public AccountMainService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public AccountDto createAccountDto(AccountDto accountDto) {
        if (accountDto.accountName().isEmpty())
            throw new AccountCreateException("Account Name is required");
        Account account = AccountMapper.toAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.toDto(savedAccount);

    }

    @Override
    public AccountDto getAccountDto(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountException("Account not found"));
        return AccountMapper.toDto(account);
    }

    @Override
    public AccountDto deposit(Long accountId, Double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountException("Account not found"));
        if (amount <= 0)
            throw new AmountException("Amount must be greater than 0");
        double totaBal = account.getBalance() + amount;
        account.setBalance(totaBal);
        Account savedAccount = accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setTransactionType(TRANSACTION_TYPE_DEPOSIT);
        transaction.setAmount(amount);
        transaction.setTransactionTime(LocalDateTime.now());
        transactionRepository.save(transaction);
        return AccountMapper.toDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long accountId, Double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountException("Account not found"));
        if (amount <= 0)
            throw new AmountException("Amount must be greater than 0");

        double totaBal = account.getBalance() - amount;
        account.setBalance(totaBal);
        Account savedAccount = accountRepository.save(account);
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TRANSACTION_TYPE_WITHDRAW);
        transaction.setAccountId(accountId);
        transaction.setAmount(amount);
        transaction.setTransactionTime(LocalDateTime.now());
        transactionRepository.save(transaction);
        return AccountMapper.toDto(savedAccount);

    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(AccountMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountException("Account not found"));
        accountRepository.delete(account);
    }

    @Override
    public void remittance(TransactionFoundDto transactionFoundDto) {
       Account fromAccount = accountRepository.findById(transactionFoundDto.fromAccountId())
               .orElseThrow(() -> new AccountException("Account not found"));
        Account toAccount = accountRepository.findById(transactionFoundDto.toAccountId())
                .orElseThrow(() -> new AccountException("Account not found"));
        if(fromAccount.getBalance() < transactionFoundDto.amount())
            throw new AmountException("Insufficient balance");
        
        fromAccount.setBalance(fromAccount.getBalance() - transactionFoundDto.amount());
        toAccount.setBalance(toAccount.getBalance() + transactionFoundDto.amount());
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction transaction = new Transaction();
        transaction.setAccountId(transactionFoundDto.fromAccountId());
        transaction.setAmount(transactionFoundDto.amount());
        transaction.setTransactionType(TRANSACTION_TYPE_REMITTANCE);
        transaction.setTransactionTime(LocalDateTime.now());
        transactionRepository.save(transaction);

    }

    @Override
    public List<TransactionDto> getTransactions(Long accountId) {
        List<Transaction> transactions = transactionRepository.findByAccountId(accountId);
        return transactions.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private TransactionDto convertEntityToDto(Transaction transaction) {
        return new TransactionDto(transaction.getId(), transaction.getAccountId(), transaction.getTransactionType(), transaction.getAmount(), transaction.getTransactionTime());

}
}
