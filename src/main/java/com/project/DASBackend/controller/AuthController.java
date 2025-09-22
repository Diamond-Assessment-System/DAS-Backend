package com.project.DASBackend.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.project.DASBackend.dto.AccountDto;
import com.project.DASBackend.entity.Account;
import com.project.DASBackend.exception.ResourceNotFoundException;
import com.project.DASBackend.repository.AccountRepository;
import com.project.DASBackend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.amplify.model.UnauthorizedException;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> loginWithFirebaseToken(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid authorization header.");
        }

        String idToken = authorizationHeader.substring(7);

        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String uid = decodedToken.getUid();
            String email = decodedToken.getEmail();
            String displayName = decodedToken.getName();

            Account account = accountRepository.findByUid(uid);
            if (account == null) {
                account = Account.builder()
                        .uid(uid)
                        .email(email)
                        .displayName(displayName)
                        .accountStatus(1)
                        .role(1)
                        .build();
                accountRepository.save(account);
            }

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String sessionId = authentication.getDetails().toString();

            return ResponseEntity.ok(Map.of(
                    "sessionId", sessionId,
                    "account", account
            ));
        } catch (FirebaseAuthException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Error verifying token: " + e.getMessage());
        }
    }

    @PostMapping("/registerPhone")
    public ResponseEntity<?> registerWithPhoneNumber(@RequestBody AccountDto accountDto) {
        accountService.phoneregister(accountDto);
        return ResponseEntity.ok("Account created successfully");
    }

    @PostMapping("/loginPhone")
    public ResponseEntity<?> loginWithPhoneNumber(@RequestBody Map<String, String> loginRequest) {
        String phone = loginRequest.get("phone");
        String password = loginRequest.get("password");

        try {
            AccountDto accountDto = accountService.phonelogin(phone, password);
            String sessionId = UUID.randomUUID().toString();

            return ResponseEntity.ok(Map.of(
                    "sessionId", sessionId,
                    "account", accountDto
            ));
        } catch (ResourceNotFoundException | UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

}
