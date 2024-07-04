package com.project.DASBackend.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.project.DASBackend.entity.Account;
import com.project.DASBackend.repository.AccountRepository;
import com.project.DASBackend.service.AccountService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

//    @PostMapping("/login")
//    public ResponseEntity<?> loginWithFirebaseToken(@RequestHeader("Authorization") String authorizationHeader) {
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            String idToken = authorizationHeader.substring(7);
//
//            try {
//                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
//                String uid = decodedToken.getUid();
//                String email = decodedToken.getEmail();
//                String displayName = decodedToken.getName();
//
//                Account account = accountRepository.findByUid(uid);
//                if (account == null) {
//                    account = Account.builder()
//                            .uid(uid)
//                            .email(email)
//                            .displayName(displayName)
//                            .accountStatus(1)
//                            .role(1)
//                            .build();
//                    accountRepository.save(account);
//                }
//
//                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//                String sessionId = authentication.getDetails().toString();
//
//                // Return session ID and account details as JSON object
//                return ResponseEntity.ok(Map.of(
//                        "sessionId", sessionId,
//                        "account", account
//                ));
//            } catch (FirebaseAuthException e) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error verifying token: " + e.getMessage());
//            }
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid authorization header.");
//        }
//    }

    @PostMapping("/api/login")
    public Map<String, String> login(@RequestBody Map<String, String> payload) throws AuthenticationException {
        String idToken = payload.get("idToken");

        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String uid = decodedToken.getUid();

            // Generate JWT token
            Map<String, Object> claims = new HashMap<>();
            claims.put("uid", uid);

            String jwtToken = Jwts.builder()
                    .setClaims(claims)
                    .setSubject(uid)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();

            Map<String, String> response = new HashMap<>();
            response.put("token", jwtToken);

            return response;
        } catch (FirebaseAuthException e) {
            throw new AuthenticationException("Firebase token invalid") {};
        }
    }
}