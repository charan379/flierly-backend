package com.ctytech.flierly.account.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class AccountTypeDTO implements Serializable {

    private Long id;

    @NotBlank(message = "{account.type.name.absent}")
    @Pattern(regexp = "^[a-z0-9_]+$", message = "{account.type.name.invalid}")
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<Long> subTypeIds;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<AccountSubtypeDTO> subtypes;
}
