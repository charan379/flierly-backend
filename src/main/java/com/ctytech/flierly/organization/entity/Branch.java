package com.ctytech.flierly.branch.entity;

import com.ctytech.flierly.address.entity.Address;
import com.ctytech.flierly.organization.entity.Organization;
import com.ctytech.flierly.taxation.entity.TaxIdentity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "branches")
@Getter @Setter
public class Branch implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "addressId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "branch_address_fkey"))
    private Address address;

    @NotNull
    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "taxIdentityId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "branch_tax_identity_fkey"))
    private TaxIdentity taxIdentity;

    @NotNull
    private Long phone;

    @NotNull
    private Long alternatePhone;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String country;

    @NotNull
    private String state;

    @NotNull
    private String district;

}
