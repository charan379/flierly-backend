package com.ctytech.flierly.address.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class AreaDTO implements Serializable {

    private Long id;

    @NotBlank(message = "{area.name.absent}")
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PostalIdentityDTO postalIdentity;
}
