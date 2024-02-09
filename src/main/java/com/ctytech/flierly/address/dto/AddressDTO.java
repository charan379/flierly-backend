package com.ctytech.flierly.address.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    private String line2;

    private String line3;

    @NotNull(message = "{address.country.absent}")
    private CountryDTO country;

    @NotNull(message = "{address.state.absent}")
    private StateDTO state;

    @NotNull(message = "{address.district.absent}")
    private DistrictDTO district;

    @NotNull(message = "{address.city.absent}")
    private CityDTO city;

    @NotNull(message = "{address.postalIdentity.absent}")
    private PostalIdentityDTO postalIdentity;

    @NotNull(message = "{address.area.absent}")
    private AreaDTO area;

    private String landMark;

    private BigDecimal latitude;

    private BigDecimal longitude;
}
