package com.project.DASBackend.service.impl;

import com.project.DASBackend.dto.AccountDto;
import com.project.DASBackend.entity.Account;
import com.project.DASBackend.mapper.AccountMapper;
import com.project.DASBackend.repository.AccountRepository;
import com.project.DASBackend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.toEntity(accountDto);
        account = accountRepository.save(account);
        return AccountMapper.toDto(account);
    }

    @Override
    public AccountDto getAccountById(Integer accountId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        return AccountMapper.toDto(account);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(AccountMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public AccountDto updateAccount(Integer accountId, AccountDto accountDto) {
        Account account = AccountMapper.toEntity(accountDto);
        account.setAccountId(accountId);
        account = accountRepository.save(account);
        return AccountMapper.toDto(account);
    }

    @Override
    public void deleteAccount(Integer accountId) {
        accountRepository.deleteById(accountId);
    }
}
