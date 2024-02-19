package com.ctytech.flierly.contact.dto;

import com.ctytech.flierly.address.dto.AddressDTO;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;

@Data
public class ContactDTO implements Serializable {

    private Long id;

    @NotBlank(message = "{contact.name.absent}")
    private String name;

    @Digits(integer = 13, fraction = 0, message = "{phone.invalid}")
    @NotBlank(message = "{contact.phone.absent}")
    private String phone;

    @Digits(integer = 13, fraction = 0, message = "{phone.invalid}")
    private String alternatePhone;

    @Email(message = "{email.invalid}")
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$", message = "{email.invalid}")
    @NotBlank(message = "{contact.email.absent}")
    private String email;

    private AddressDTO address;
}
