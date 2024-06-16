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

    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Display name cannot be blank")
    private String displayName;

    private String uid;

    @NotNull(message = "Account status cannot be null")
    private Integer accountStatus;

    @NotNull(message = "Role cannot be null")
    private Integer role;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotBlank(message = "Phone cannot be blank")
    private String phone;
}
