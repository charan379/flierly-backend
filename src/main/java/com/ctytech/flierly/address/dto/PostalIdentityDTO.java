package com.ctytech.flierly.address.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
public class PostalIdentityDTO implements Serializable {

    private Long id;

    private Integer pinCode;

    private CityDTO city;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<AreaDTO> areas;
}
