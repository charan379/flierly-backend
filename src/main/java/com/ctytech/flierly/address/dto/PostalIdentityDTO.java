package com.ctytech.flierly.address.dto;

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

    private CityDTO city;

    private CountryDTO country;
}
