package com.ctytech.flierly.account.dto;

import com.ctytech.flierly.address.dto.AddressDTO;
import com.ctytech.flierly.contact.dto.ContactDTO;
import com.ctytech.flierly.organization.dto.BranchDTO;
import com.ctytech.flierly.taxation.dto.TaxIdentityDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class AccountDTO implements Serializable {

    private Long id;

    @JsonSetter(nulls = Nulls.SKIP)
    private Boolean isVip = false;

    @JsonSetter(nulls = Nulls.SKIP)
    private Boolean isKey = false;

    @NotBlank(message = "{account.name.absent}")
    private String name;

    @NotBlank(message = "{account.registered.phone.absent}")
    @Digits(integer = 13, fraction = 0, message = "{phone.invalid}")
    private String registeredPhone;

    @Digits(integer = 13, fraction = 0, message = "{phone.invalid}")
    private String alternatePhone;

    @Email(message = "{email.invalid}")
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$", message = "{email.invalid}")
    @NotBlank(message = "{account.registered.email.absent}")
    private String email;

    @NotNull(message = "{account.branch.absent}")
    private BranchDTO branch;

    @NotNull(message = "{account.type.absent}")
    private AccountTypeDTO accountType;

    @NotNull(message = "{account.subtype.absent}")
    private AccountSubtypeDTO accountSubtype;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TaxIdentityDTO taxIdentity;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<ContactDTO> contacts;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<AddressDTO> addresses;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<AccountDTO> childAccounts;

    private AccountDTO parentAccount;
}
