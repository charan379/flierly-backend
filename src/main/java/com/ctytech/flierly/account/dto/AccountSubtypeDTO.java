package com.ctytech.flierly.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;

@Data
public class AccountSubtypeDTO implements Serializable {

    private Long id;

    @NotBlank(message = "account.subtype.name.absent")
    @Pattern(regexp = "^[a-z0-9_]+$", message = "account.subtype.name.invalid")
    private String name;
}
