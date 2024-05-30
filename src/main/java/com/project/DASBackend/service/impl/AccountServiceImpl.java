package com.project.DASBackend.service.impl;

import com.project.DASBackend.dto.AccountDto;
import com.project.DASBackend.dto.GoogleLoginRequest;
import com.project.DASBackend.entity.Account;
import com.project.DASBackend.exception.ResourceNotFoundException;
import com.project.DASBackend.mapper.AccountMapper;
import com.project.DASBackend.repository.AccountRepository;
import com.project.DASBackend.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account handleGoogleLogin(GoogleLoginRequest googleLoginRequest) {
        Optional<Account> existingAccount = accountRepository.findByEmail(googleLoginRequest.getEmail());

        Account account;
        if (existingAccount.isPresent()) {
            account = existingAccount.get();
            account.setGoogleAccessToken(googleLoginRequest.getGoogleAccessToken());
            account.setRefreshToken(googleLoginRequest.getRefreshToken());
            account.setAccessTokenExpiryTime(googleLoginRequest.getAccessTokenExpiryTime());
            account.setAccountStatus(googleLoginRequest.getAccountStatus());
            account.setRole(googleLoginRequest.getRole());
        } else {
            account = Account.builder()
                    .email(googleLoginRequest.getEmail())
                    .firstName(googleLoginRequest.getFirstName())
                    .lastName(googleLoginRequest.getLastName())
                    .googleAccessToken(googleLoginRequest.getGoogleAccessToken())
                    .refreshToken(googleLoginRequest.getRefreshToken())
                    .accessTokenExpiryTime(googleLoginRequest.getAccessTokenExpiryTime())
                    .accountStatus(googleLoginRequest.getAccountStatus())
                    .role(googleLoginRequest.getRole())
                    .build();
        }

        accountRepository.save(account);
        return account;
    }



    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.toEntity(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.toDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Integer accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with given Id: " + accountId));
        return AccountMapper.toDto(account);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account -> AccountMapper.toDto(account)))
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto updateAccount(Integer accountId, AccountDto updatedAccountDto) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account is not exist with given Id: " + accountId));

        account.setEmail(updatedAccountDto.getEmail());
        account.setFirstName(updatedAccountDto.getFirstName());
        account.setLastName(updatedAccountDto.getLastName());
        account.setGoogleAccessToken(updatedAccountDto.getGoogleAccessToken());
        account.setRefreshToken(updatedAccountDto.getRefreshToken());
        account.setAccessTokenExpiryTime(updatedAccountDto.getAccessTokenExpiryTime());
        account.setAccountStatus(updatedAccountDto.getAccountStatus());
        account.setRole(updatedAccountDto.getRole());

        Account updatedAccount = accountRepository.save(account);
        return AccountMapper.toDto(updatedAccount);
    }

    @Override
    public void deleteAccount(Integer accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account is not exist with given Id: " + accountId));
        accountRepository.deleteById(accountId);
    }
}
