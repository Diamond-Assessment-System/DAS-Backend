package com.project.DASBackend.service;

import com.project.DASBackend.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);

    AccountDto phoneregister(AccountDto accountDto);

    AccountDto phonelogin(String phone, String password);

    AccountDto getAccountById(Integer accountId);

    List<AccountDto> getAllAccounts();

    List<AccountDto> getAccountsByRole(Integer role);

    AccountDto updateAccount(Integer accountId, AccountDto accountDto);

    void deleteAccount(Integer accountId);

    AccountDto changeStatus(Integer accountId, Integer status);
    AccountDto changeRole(Integer accountId, Integer role);
}
