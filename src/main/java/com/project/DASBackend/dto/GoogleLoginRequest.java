package com.project.DASBackend.dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GoogleLoginRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String googleAccessToken;
    private String refreshToken;
    private LocalDateTime accessTokenExpiryTime;
    private Integer accountStatus;
    private Integer role;
}
