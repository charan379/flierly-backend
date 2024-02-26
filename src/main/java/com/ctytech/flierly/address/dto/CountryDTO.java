package com.ctytech.flierly.address.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class CountryDTO implements Serializable {

    private Long id;

    @NotBlank(message = "{country.code.absent}")
    @Size(max = 100, message = "{code.size.invalid}")
    @Pattern(regexp = "^[a-z_]+$", message = "{code.pattern.invalid}")
    private String code;

    @NotBlank(message = "{country.name.absent}")
    private String name;

    @NotNull(message = "{country.dialingCode.absent}")
    private Integer dialingCode;
}
