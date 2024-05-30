package com.project.DASBackend.service;

import com.project.DASBackend.dto.AccountDto;
import com.project.DASBackend.dto.GoogleLoginRequest;
import com.project.DASBackend.entity.Account;

import java.util.List;

public interface AccountService{

    public Account handleGoogleLogin(GoogleLoginRequest googleLoginRequest);
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Integer accountId);

    List<AccountDto> getAllAccounts();

    AccountDto updateAccount(Integer accountId, AccountDto accountDto);

    void deleteAccount(Integer accountId);

}
