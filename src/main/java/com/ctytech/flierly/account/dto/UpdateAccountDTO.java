package com.ctytech.flierly.account.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateAccountDTO implements Serializable {

    private Boolean isVip;

    private Boolean isKey;

    private String name;

    @Digits(integer = 13, fraction = 0, message = "{phone.invalid}")
    private String alternatePhone;

    private Long accountTypeId;

    private Long accountSubtypeId;
}
