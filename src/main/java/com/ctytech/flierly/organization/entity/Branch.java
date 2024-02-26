package com.ctytech.flierly.organization.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "branches")
@SequenceGenerator(name = "branch_id_generator", sequenceName = "branch_id_seq", initialValue = 1000, allocationSize = 1)
@Getter
@Setter
@EqualsAndHashCode
public class Branch implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branch_id_generator")
    private Long id;

    @NotBlank(message = "{branch.name.absent}")
    private String name;

    @Column(columnDefinition = "boolean default false")
    private Boolean isActive;

    private Long addressId;

    private Long taxIdentityId;

    @Digits(integer = 13, fraction = 0, message = "{phone.invalid}")
    @NotBlank(message = "{branch.phone.absent}")
    @Column(unique = true, nullable = false, length = 13)
    private String phone;

    @Digits(integer = 13, fraction = 0, message = "{phone.invalid}")
    @Column(length = 13)
    private String alternatePhone;

    @Email(message = "{email.invalid}")
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$", message = "{email.invalid}")
    @NotBlank(message = "{branch.email.absent}")
    @Column(unique = true, nullable = false)
    private String email;
}