package com.project.DASBackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AccountDto {

    private Integer accountId;

    private String email;

    private String displayName;

    private String uid;

    private Integer accountStatus;

    private Integer role;

    private String password;

    private String phone;

    private String blockReason;
}
