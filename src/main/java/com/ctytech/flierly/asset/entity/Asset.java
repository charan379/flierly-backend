package com.ctytech.flierly.asset.entity;

import com.ctytech.flierly.account.entity.Account;
import com.ctytech.flierly.organization.entity.Branch;
import com.ctytech.flierly.salesandpurchase.entity.PurchaseInvoice;
import com.ctytech.flierly.salesandpurchase.entity.SalesInvoice;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "assets")
@Getter @Setter @EqualsAndHashCode
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "branchId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "asset_branch_fkey"), updatable = false)
    private Branch branch;

    @NotNull
    private String serialNumber;

    private String imei1;

    private String imei2;

    private LocalDate manufacturingDate;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "purchaseInvId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "asset_purchase_invoice_fkey"))
    private PurchaseInvoice purchaseInvoice;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "salesInvId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "asset_sales_invoice_fkey"))
    private SalesInvoice salesInvoice;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "soldToAccId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "asset_sold_to_account_fkey"))
    private Account soldToAccount;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "purchasedFromAccId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "asset_purchased_from_account_fkey"))
    private Long purchasedFromAccount;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "asset_parent_product_fkey"))
    private Long product;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "assetStatusId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "asset_asset_status_fkey"))
    private AssetStatus assetStatus;
}
