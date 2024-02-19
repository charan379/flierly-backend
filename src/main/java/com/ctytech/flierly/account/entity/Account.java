package com.ctytech.flierly.account.entity;

import com.ctytech.flierly.address.entity.Address;
import com.ctytech.flierly.contact.enitity.Contact;
import com.ctytech.flierly.organization.entity.Branch;
import com.ctytech.flierly.taxation.entity.TaxIdentity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accounts")
@SequenceGenerator(name = "accounts_id_generator", sequenceName = "accounts_id_seq", initialValue = 1000, allocationSize = 1)
@Getter
@Setter
@EqualsAndHashCode
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accounts_id_generator")
    private Long id;

    @Column(columnDefinition = "boolean default false")
    private Boolean isVip;

    @Column(columnDefinition = "boolean default false")
    private Boolean isKey;

    @NotBlank(message = "{account.name.absent}")
    private String name;

    @NotBlank(message = "{account.registered.phone.absent}")
    @Digits(integer = 13, fraction = 0, message = "{phone.invalid}")
    @Column(unique = true, nullable = false, length = 13)
    private String registeredPhone;

    @Digits(integer = 13, fraction = 0, message = "{phone.invalid}")
    @Column(length = 13)
    private String alternatePhone;

    @Email(message = "{email.invalid}")
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$", message = "{email.invalid}")
    @NotBlank(message = "{account.registered.email.absent}")
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull(message = "{account.branch.absent}")
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "branchId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "account_branch_fkey"), updatable = false)
    private Branch branch;

    @NotNull(message = "{account.type.absent}")
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "accountType", referencedColumnName = "id", foreignKey = @ForeignKey(name = "account_account_type_fkey"))
    private AccountType accountType;

    @NotNull(message = "{account.subtype.absent}")
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "accountSubtype", referencedColumnName = "id", foreignKey = @ForeignKey(name = "account_account_subtype_fkey"))
    private AccountSubtype accountSubtype;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "taxIdentity", referencedColumnName = "id", foreignKey = @ForeignKey(name = "account_tax_identity_fkey"), updatable = false)
    private TaxIdentity taxIdentity;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinTable(name = "accountContacts", joinColumns = {@JoinColumn(name = "accountId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "account_contacts_account_fkey"))}, inverseJoinColumns = {@JoinColumn(name = "contactId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "account_contacts_contact_fkey"))})
    private Set<Contact> contacts = new HashSet<>();

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinTable(name = "accountAddresses", joinColumns = {@JoinColumn(name = "accountId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "accounts_addresses_account_id_fkey"))}, inverseJoinColumns = {@JoinColumn(name = "addressId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "account_addresses_address_id_fkey"))})
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinTable(name = "accountsLineage", joinColumns = {@JoinColumn(name = "parentAccountId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "accounts_lineage_parent_acc_fkey"))}, inverseJoinColumns = {@JoinColumn(name = "childAccountId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "accounts_lineage_child_acc_fkey"))})
    private Set<Account> childAccounts = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Account parentAccount;
}
