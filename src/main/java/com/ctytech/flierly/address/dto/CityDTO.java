package com.ctytech.flierly.address.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class CityDTO implements Serializable {

    private Long id;

    @NotBlank(message = "{city.code.absent}")
    @Size(max = 100, message = "{code.size.invalid}")
    @Pattern(regexp = "^[a-z_]+$", message = "{code.pattern.invalid}")
    private String code;

    @NotBlank(message = "{city.name.absent}")
    private String name;

    private DistrictDTO district;
}
