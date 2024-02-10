package com.ctytech.flierly.address.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class StateDTO implements Serializable {

    private Long id;

    @NotBlank(message = "{state.code.absent}")
    @Size(max = 100, message = "{code.size.invalid}")
    @Pattern(regexp = "^[a-z_]+$", message = "{code.pattern.invalid}")
    private String code;

    @NotEmpty(message = "{state.name.absent}")
    private String name;

    private Boolean isUnionTerritory;

    private Integer gstCode;

    private CountryDTO country;

}
