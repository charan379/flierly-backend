package com.ctytech.flierly.asset.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter @Setter @EqualsAndHashCode
public class AssetDTO implements Serializable {

    private Long id;

//    BranchDTO

    private String serialNumber;

    private String imei1;

    private String imei2;

    private LocalDate manufacturingDate;

//    Purchase Invoice DTO

//    Sales Invoice DTO

//    Sold To Account DTO

//    Purchased From Account DTO

//    Product DTO

    private AssetStatusDTO assetStatus;

}
