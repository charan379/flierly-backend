package com.ctytech.flierly.salesandpurchase.entity;

import com.ctytech.flierly.organization.entity.Branch;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "purchaseInvoices")
@Getter @Setter @EqualsAndHashCode
public class PurchaseInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "branchId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "purchase_invoice_and_branch_fkey"))
    private Branch branch;
}
