package com.project.DASBackend.service.impl;

import com.project.DASBackend.dto.AccountDto;
import com.project.DASBackend.entity.Account;
import com.project.DASBackend.exception.ResourceNotFoundException;
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
        return hidePassword(AccountMapper.toDto(account));
    }

    @Override
    public AccountDto phoneregister(AccountDto accountDto) {
        Account account = AccountMapper.toEntity(accountDto);
        account = accountRepository.save(account);
        return hidePassword(AccountMapper.toDto(account));
    }

    @Override
    public AccountDto phonelogin(String phone, String password) {
        Account account = accountRepository.findByPhoneAndPassword(phone, password)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with phone: " + phone));
        return hidePassword(AccountMapper.toDto(account));
    }

    @Override
    public AccountDto getAccountById(Integer accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + accountId));
        return hidePassword(AccountMapper.toDto(account));
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(account -> hidePassword(AccountMapper.toDto(account))).collect(Collectors.toList());
    }

    @Override
    public List<AccountDto> getAccountsByRole(Integer role) {
        List<Account> accounts = accountRepository.findByRole(role);
        return accounts.stream().map(account -> hidePassword(AccountMapper.toDto(account))).collect(Collectors.toList());
    }

    @Override
    public AccountDto updateAccount(Integer accountId, AccountDto accountDto) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + accountId));
        account.setUid(accountDto.getUid());
        account.setEmail(accountDto.getEmail());
        account.setDisplayName(accountDto.getDisplayName());
        account.setAccountStatus(accountDto.getAccountStatus());
        account.setRole(accountDto.getRole());
        account = accountRepository.save(account);
        return hidePassword(AccountMapper.toDto(account));
    }

    @Override
    public void deleteAccount(Integer accountId) {
        if (!accountRepository.existsById(accountId)) {
            throw new ResourceNotFoundException("Account not found with id: " + accountId);
        }
        accountRepository.deleteById(accountId);
    }

    @Override
    public AccountDto changeStatus(Integer accountId, Integer status) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + accountId));
        account.setAccountStatus(status);
        account = accountRepository.save(account);
        return hidePassword(AccountMapper.toDto(account));
    }

    @Override
    public AccountDto changeRole(Integer accountId, Integer role) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + accountId));
        account.setRole(role);
        account = accountRepository.save(account);
        return hidePassword(AccountMapper.toDto(account));
    }

    private AccountDto hidePassword(AccountDto accountDto) {
        if (accountDto != null) {
            accountDto.setPassword("NULL");
        }
        return accountDto;
    }
}
