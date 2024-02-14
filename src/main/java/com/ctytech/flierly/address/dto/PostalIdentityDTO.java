package com.ctytech.flierly.address.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class PostalIdentityDTO implements Serializable {

    private Long id;

    private Integer pinCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CityDTO city;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CountryDTO country;
}
