package com.ctytech.flierly.organization.entity;

import com.ctytech.flierly.address.entity.Address;
import com.ctytech.flierly.taxation.entity.TaxIdentity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "branches")
@SequenceGenerator(name = "branch_id_generator", sequenceName = "branch_id_seq", initialValue = 1000, allocationSize = 1)
@Getter
@Setter
@EqualsAndHashCode
public class Branch implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branch_id_generator")
    private Long id;

    @NotBlank(message = "{branch.name.absent}")
    private String name;

    @Column(columnDefinition = "boolean default false")
    private Boolean isActive;

    @NotNull(message = "{branch.address.absent")
    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "addressId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "branch_address_fkey"), updatable = false)
    private Address address;

    @NotNull(message = "{branch.taxId.absent}")
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "taxIdentityId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "branch_tax_identity_fkey"))
    private TaxIdentity taxIdentity;

    @Digits(integer = 13, fraction = 0, message = "{phone.invalid}")
    @NotBlank(message = "{branch.phone.absent}")
    @Column(unique = true, nullable = false, length = 13)
    private String phone;

    @Digits(integer = 13, fraction = 0, message = "{phone.invalid}")
    @Column(length = 13)
    private String alternatePhone;

    @Email(message = "{email.invalid}")
    @NotBlank(message = "{branch.email.absent}")
    @Column(unique = true, nullable = false)
    private String email;

}
