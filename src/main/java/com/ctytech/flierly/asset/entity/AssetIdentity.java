package com.ctytech.flierly.asset.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "assets")
@Getter @Setter @EqualsAndHashCode
public class AssetIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serialNumber;

    private String imei1;

    private String imei2;

    private LocalDate manufacturingDate;

    private Long purchaseInvoice;

    private Long salesInvoice;

    private Long soldToAccount;

    private Long purchasedFromAccount;

    private Long product;

    private String assetStatus;


}
