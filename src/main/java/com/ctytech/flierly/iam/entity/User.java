package com.ctytech.flierly.iam.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "iam_users", indexes = {
        @Index(name = "idx_username", columnList = "username"),
        @Index(name = "idx_email", columnList = "email"),
        @Index(name = "idx_mobile", columnList = "mobile")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @Column(columnDefinition = "boolean default true")
    private Boolean isActive;

    @NotBlank(message = "Username is required.")
    @Column(unique = true, nullable = false)
    private String username;

    @Email(message = "User email is required.")
    @NotBlank(message = "User email is required.")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "User mobile number is required.")
    @Column(unique = true, nullable = false)
    private String mobile;

    @ManyToMany
    @JoinTable(
            name = "iam_user_additional_privileges",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id")
    )
    private List<Privilege> additionalPrivileges;

    @ManyToMany
    @JoinTable(
            name = "iam_user_restricted_privileges",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id")
    )
    private List<Privilege> restrictedPrivileges;

    @ManyToMany
    @JoinTable(
            name = "iam_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
}
