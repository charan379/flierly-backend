package com.ctytech.flierly.organization.dto;


import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class OrganizationDTO implements Serializable {

    private Long id;

    @NotBlank(message = "{organization.name.absent}")
    private String name;

    @Email(message = "{email.invalid}")
    @NotBlank(message = "{organization.email.absent}")
    private String email;

    @Digits(integer = 13, fraction = 0, message = "{phone.invalid}")
    @NotNull(message = "{organization.phone.absent}")
    private String phone;
}
