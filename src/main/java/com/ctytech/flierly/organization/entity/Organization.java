package com.ctytech.flierly.organization.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "organization")
@SequenceGenerator(name = "organization_id_generator", sequenceName = "organization_id_seq", initialValue = 1000, allocationSize = 1)
@Getter
@Setter
@EqualsAndHashCode
public class Organization implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_id_generator")
    private Long id;

    @NotBlank(message = "{organization.name.absent}")
    @Column(unique = true, nullable = false)
    private String name;

    @Email(message = "{email.invalid}")
    @NotBlank(message = "{organization.email.absent}")
    @Column(unique = true, nullable = false)
    private String email;

    @Digits(integer = 13, fraction = 0, message = "{phone.invalid}")
    @NotBlank(message = "{organization.phone.absent}")
    @Column(unique = true, nullable = false)
    private String phone;
}