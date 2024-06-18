package com.project.DASBackend.service.impl;

import java.util.ArrayList;

import com.project.DASBackend.dto.AccountDto;
import com.project.DASBackend.entity.Account;
import com.project.DASBackend.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder bcryptEncoder;

    @Autowired
    public JwtUserDetailsService(AccountRepository accountRepository, @Lazy PasswordEncoder bcryptEncoder) {
        this.accountRepository = accountRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        Account account = accountRepository.findByUid(uid);
        if (account == null) {
            throw new UsernameNotFoundException("User not found with username: " + uid);
        }
        return new User(account.getUid(), account.getPassword(), new ArrayList<>());
    }

    public Account save(AccountDto accountDto) {
        Account newAccount = new Account();
        newAccount.setUid(accountDto.getUid());
        newAccount.setEmail(accountDto.getEmail());
        newAccount.setDisplayName(accountDto.getDisplayName());
        newAccount.setAccountStatus(accountDto.getAccountStatus());
        newAccount.setRole(accountDto.getRole());
        newAccount.setPhone(accountDto.getPhone());
        newAccount.setPassword(bcryptEncoder.encode(accountDto.getPassword()));
        return accountRepository.save(newAccount);
    }
}
