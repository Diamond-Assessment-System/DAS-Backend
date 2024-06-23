package com.project.DASBackend.controller;

import com.project.DASBackend.dto.AccountDto;
import com.project.DASBackend.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody AccountDto accountDto){
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
    public ResponseEntity<AccountDto> updateAccount(@Valid @RequestBody AccountDto accountDto,
                                                    @PathVariable("id") Integer accountId){
        return ResponseEntity.ok(accountService.updateAccount(accountId, accountDto));
    }

    @GetMapping("role/{id}")
    public ResponseEntity<List<AccountDto>> getAccountsByRole(@PathVariable("id") Integer role){
        List<AccountDto> accountDtos = accountService.getAccountsByRole(role);
        return ResponseEntity.ok(accountDtos);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") Integer accountId){
        accountService.deleteAccount(accountId);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
