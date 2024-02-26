package com.ctytech.flierly.asset.dto;

import java.io.Serializable;

public class AssetPageRequestDTO implements Serializable {

    private Long branchId;

    private String serialNumber;

    private String imei1;

    private String imei2;

    private Long purchaseInv;

    private Long salesInv;

    private Long soldTo;

    private Long purchasedFrom;

    private Long productId;

    private String status;

    private Integer pageNo;

    private Integer resultsPerPage;

    private String sort;
}
