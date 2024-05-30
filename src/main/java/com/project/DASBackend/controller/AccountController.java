package com.project.DASBackend.controller;

import com.project.DASBackend.dto.AccountDto;
import com.project.DASBackend.dto.GoogleLoginRequest;
import com.project.DASBackend.entity.Account;
import com.project.DASBackend.repository.AccountRepository;
import com.project.DASBackend.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;
    private AccountRepository accountRepository;

    @PostMapping("/google-login")
    public ResponseEntity<?> googleLogin(@RequestBody GoogleLoginRequest googleLoginRequest) {
        Optional<Account> existingAccount = accountRepository.findByEmail(googleLoginRequest.getEmail());

        if (existingAccount.isPresent()) {
            Account account = existingAccount.get();
            account.setGoogleAccessToken(googleLoginRequest.getGoogleAccessToken());
            account.setRefreshToken(googleLoginRequest.getRefreshToken());
            account.setAccessTokenExpiryTime(googleLoginRequest.getAccessTokenExpiryTime());
            account.setAccountStatus(googleLoginRequest.getAccountStatus());
            account.setRole(googleLoginRequest.getRole());
            accountRepository.save(account);

            return ResponseEntity.ok("User logged in successfully");
        } else {
            Account newAccount = Account.builder()
                    .email(googleLoginRequest.getEmail())
                    .firstName(googleLoginRequest.getFirstName())
                    .lastName(googleLoginRequest.getLastName())
                    .googleAccessToken(googleLoginRequest.getGoogleAccessToken())
                    .refreshToken(googleLoginRequest.getRefreshToken())
                    .accessTokenExpiryTime(googleLoginRequest.getAccessTokenExpiryTime())
                    .accountStatus(googleLoginRequest.getAccountStatus())
                    .role(googleLoginRequest.getRole())
                    .build();

            accountRepository.save(newAccount);

            return ResponseEntity.ok("User registered successfully");
        }
    }


    @PostMapping
    public ResponseEntity<AccountDto> createAccount(AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") Integer accountId){
        AccountDto accountDto = accountService.getAccountById(accountId);
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accountDtos = accountService.getAllAccounts();
        return ResponseEntity.ok(accountDtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<AccountDto> updateAccount(@RequestBody AccountDto accountDto,
                                                    @PathVariable("id") Integer accountId){
        return ResponseEntity.ok(accountService.updateAccount(accountId, accountDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") Integer accountId){
        accountService.deleteAccount(accountId);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
