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
@Table(name = "iam_privileges", indexes = {
        @Index(name = "idx_module", columnList = "module")
})
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Privilege name is required.")
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank(message = "Access type is required.")
    @Column(nullable = false)
    private String access;

    @NotBlank(message = "Module name is required.")
    @Column(nullable = false)
    private String module;

    @NotBlank(message = "Privilege code is required.")
    @Column(nullable = false, unique = true)
    private String code;
}