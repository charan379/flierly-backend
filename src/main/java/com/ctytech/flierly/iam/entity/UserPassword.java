package com.ctytech.flierly.iam.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "iam_user_passwords")
public class UserPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "UserId is required for storing passwords")
    @Column(nullable = false, unique = true)
    private Long userId;

    @NotBlank(message = "User password is required.")
    @Column(nullable = false)
    private String password;
}
