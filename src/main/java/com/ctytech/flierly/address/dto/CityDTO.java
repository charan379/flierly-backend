package com.ctytech.flierly.address.dto;

import com.ctytech.flierly.address.entity.District;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
public class CityDTO implements Serializable {

    private Long id;

    @NotBlank(message = "{city.name.absent}")
    private String name;

    private DistrictDTO district;
}
