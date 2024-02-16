package com.ctytech.flierly.organization.dto;

import com.ctytech.flierly.address.dto.AddressDTO;
import com.ctytech.flierly.taxation.dto.TaxIdentityDTO;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class BranchDTO implements Serializable {

    private Long id;

    @NotBlank(message = "{branch.name.absent}")
    private String name;

    private Boolean isActive;

    private AddressDTO address;

    private TaxIdentityDTO taxIdentity;

    @Digits(integer = 13, fraction = 0, message = "{phone.invalid}")
    @NotBlank(message = "{branch.phone.absent}")
    private String phone;

    @Digits(integer = 13, fraction = 0, message = "{phone.invalid}")
    private String alternatePhone;

    @Email(message = "{email.invalid}")
    @NotBlank(message = "{branch.email.absent}")
    private String email;
}
