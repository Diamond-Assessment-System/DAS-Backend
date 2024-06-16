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
                .uid(account.getUid())
                .email(account.getEmail())
                .displayName(account.getDisplayName())
                .accountStatus(account.getAccountStatus())
                .role(account.getRole())
                .build();
    }

    public static Account toEntity(AccountDto accountDto) {
        if (accountDto == null) {
            return null;
        }
        Account account = new Account();
        account.setAccountId(accountDto.getAccountId());
        account.setUid(accountDto.getUid());
        account.setEmail(accountDto.getEmail());
        account.setDisplayName(accountDto.getDisplayName());
        account.setAccountStatus(accountDto.getAccountStatus());
        account.setRole(accountDto.getRole());
        return account;
    }
}
