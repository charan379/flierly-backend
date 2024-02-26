package com.ctytech.flierly.uom.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode @AllArgsConstructor
public class UomConversionPageRequestDTO {

    private String code;

    private String name;

    private Long fromUomId;

    private Long toUomId;

    private Integer pageNo;

    private Integer resultsPerPage;

    private String sort;

}
