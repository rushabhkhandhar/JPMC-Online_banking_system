package com.example.demo.mapper;

import com.example.demo.dto.AccountDto;
import com.example.demo.entity.Account;
public class AccountMapper {
    public static Account toEntity(AccountDto accountDto) {
        Account account = new Account(accountDto.id(), accountDto.accountName(), accountDto.balance());
        return account;
    }

    public static AccountDto toDto(Account account) {
        AccountDto accountDto = new AccountDto(account.getId(), account.getAccountName(), account.getBalance());
        return accountDto;
    }

    
}

