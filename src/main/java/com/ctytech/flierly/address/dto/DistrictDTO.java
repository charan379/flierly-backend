package com.ctytech.flierly.address.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
public class DistrictDTO implements Serializable {

    private Long id;

    @NotBlank(message = "{district.code.absent}")
    @Size(max = 100, message = "{code.size.invalid}")
    @Pattern(regexp = "^[a-z_]+$", message = "{code.pattern.invalid}")
    private String code;

    @NotBlank(message = "{district.name.absent}")
    private String name;

    private Integer landlineCode;

    private StateDTO state;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<CityDTO> cities;
}
