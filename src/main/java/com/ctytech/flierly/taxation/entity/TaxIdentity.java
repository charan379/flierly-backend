package com.ctytech.flierly.taxation.entity;

import com.ctytech.flierly.organization.entity.Organization;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "taxIdentities", uniqueConstraints = {
        // orgId + gst
        @UniqueConstraint(name = "uniqueOrgIdAndGst", columnNames = {"orgId", "gst"}),
        // orgId + vat
        @UniqueConstraint(name = "uniqueOrgIdAndVat", columnNames = {"orgId", "vat" })
})
public class TaxIdentity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "orgId" ,referencedColumnName = "id", foreignKey = @ForeignKey(name = "tax_identity_org_fkey"))
    private Organization organization;

    @NotNull
    private String country;

    @NotNull
    private String state;

    @NotNull
    private Integer stateCode;

    private String gst;

//    private String gstType;

    private LocalDate gstRegistrationDate;

    private String pan;

    private String vat;


}
