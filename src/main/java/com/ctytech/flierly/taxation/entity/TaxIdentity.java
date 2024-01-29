package com.ctytech.flierly.taxation.entity;

import com.ctytech.flierly.organization.entity.Branch;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter @Setter @EqualsAndHashCode
@Table(name = "taxIdentities", uniqueConstraints = {
        // branchId + gst
        @UniqueConstraint(columnNames = {"branchId", "gst"}, name = "branch_and_gst_ukey"),
        // branchId + vat
        @UniqueConstraint(columnNames = {"branchId", "vat" }, name = "branch_and_vat_ukey"),
        // branchId + pan
        @UniqueConstraint(columnNames = {"branchId", "pan" }, name = "branch_and_pan_ukey"),
        // branchId + tin
        @UniqueConstraint(columnNames = {"branchId", "tin"}, name = "branch_and_tin_ukey")
})
public class TaxIdentity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "branchId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "tax_identity_and_branch_fkey"), updatable = false)
    private Branch branch;

    private String gst;

//    private String gstType;

    private LocalDate gstRegistrationDate;

    private Boolean gstVerified;

    private String pan;

    private  Boolean panVerified;

    private String vat;

    private String tin;
}
