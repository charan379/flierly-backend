package com.ctytech.flierly.address.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
public class AddressDTO implements Serializable {

    private Long id;

    private Boolean isActive;

    private Boolean isPrimary;

    @NotBlank(message = "{address.line1.absent}")
    private String line1;

    @NotBlank(message = "{address.line2.absent}")
    private String line2;

    private String line3;

    private CountryDTO country;

    private StateDTO state;

    private DistrictDTO district;

    private CityDTO city;

    private PostalIdentityDTO postalIdentity;

    private AreaDTO area;

    @NotBlank(message = "{address.landmark.absent}")
    private String landMark;

    private BigDecimal latitude;

    private BigDecimal longitude;
}
