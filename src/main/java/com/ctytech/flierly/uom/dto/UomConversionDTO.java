package com.ctytech.flierly.uom.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter @EqualsAndHashCode
public class UomConversionDTO {

    private Long id;

    private String code;

    private String name;

    private UomDTO fromUom;

    private UomDTO uomDTO;

    private BigDecimal conversionFactor;
}
