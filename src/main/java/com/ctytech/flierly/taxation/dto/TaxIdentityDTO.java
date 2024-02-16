package com.ctytech.flierly.taxation.dto;

import com.ctytech.flierly.address.dto.AddressDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
public class TaxIdentityDTO implements Serializable {

    private Long id;

    private Boolean isActive;

    @Size(min = 15, max = 15, message = "{taxIdentity.gst.invalid}")
    @Pattern(regexp = "^[0-9a-z]+$", message = "{gst.pattern.invalid}")
    private String gst;

    @PastOrPresent(message = "{taxIdentity.gstRegDate.invalid}")
    private LocalDate gstRegistrationDate;

    private Boolean gstVerified;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AddressDTO gstRegistrationAddress;

    @Size(min = 10, max = 10, message = "{taxIdentity.pan.invalid}")
    @Pattern(regexp = "^[0-9a-z]+$", message = "{pan.pattern.invalid}")
    private String pan;

    private Boolean panVerified;

    private String vat;

    private String tin;

}
