package com.project.DASBackend.service;

import com.project.DASBackend.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Integer accountId);

    List<AccountDto> getAllAccounts();

    List<AccountDto> getAccountsByRole(Integer role);

    AccountDto updateAccount(Integer accountId, AccountDto accountDto);

    void deleteAccount(Integer accountId);

    AccountDto changeStatus(Integer accountId, Integer status);
}
