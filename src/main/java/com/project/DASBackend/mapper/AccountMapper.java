package com.project.DASBackend.mapper;

import com.project.DASBackend.dto.AccountDto;
import com.project.DASBackend.entity.Account;

public class AccountMapper {
    public static AccountDto toDto(Account account) {
        if (account == null) {
            return null;
        }
        return AccountDto.builder()
                .accountId(account.getAccountId())
                .email(account.getEmail())
                .displayName(account.getDisplayName())
                .uid(account.getUid())
                .accountStatus(account.getAccountStatus())
                .role(account.getRole())
                .password(account.getPassword())
                .phone(account.getPhone())
                .build();
    }

    public static Account toEntity(AccountDto accountDto) {
        if (accountDto == null) {
            return null;
        }
        Account account = new Account();
        account.setAccountId(accountDto.getAccountId());
        account.setEmail(accountDto.getEmail());
        account.setDisplayName(accountDto.getDisplayName());
        account.setUid(accountDto.getUid());
        account.setAccountStatus(accountDto.getAccountStatus());
        account.setRole(accountDto.getRole());
        account.setPassword(accountDto.getPassword());
        account.setPhone(accountDto.getPhone());
        return account;
    }
}
