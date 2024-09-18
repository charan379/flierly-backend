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
@Table(name = "iam_roles", indexes = {
})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Role name is required.")
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank(message = "Role code is required.")
    @Column(nullable = false, unique = true)
    private String code;

    @NotBlank(message = "Role description is required.")
    @Column(nullable = false)
    private String description;
}
