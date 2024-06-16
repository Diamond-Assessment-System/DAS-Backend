package com.project.DASBackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Account")
@Data
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Account_Id")
    private Integer accountId;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "DisplayName", nullable = false)
    private String displayName;

    @Column(name = "UID")
    private String uid;

    @Column(name = "Account_status", nullable = false)
    private Integer accountStatus;

    @Column(name = "Role", nullable = false)
    private Integer role;

    @Column(name = "Password")
    private String password;

    @Column(name = "Phone")
    private String phone;
}
