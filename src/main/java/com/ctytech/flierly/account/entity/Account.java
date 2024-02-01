package com.ctytech.flierly.account.entity;

import com.ctytech.flierly.contact.enitity.Contact;
import com.ctytech.flierly.organization.entity.Branch;
import com.ctytech.flierly.taxation.entity.TaxIdentity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accounts")
@Getter @Setter @EqualsAndHashCode
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Boolean isVip;

    @NotNull
    private Boolean isKey;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "branchId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "account_branch_fkey"), updatable = false)
    private Branch branch;

    @NotNull
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "accountType", referencedColumnName = "id", foreignKey = @ForeignKey(name = "account_account_type_fkey"))
    private AccountType accountType;

    @NotNull
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "accountSubtype", referencedColumnName = "id", foreignKey = @ForeignKey(name = "account_account_subtype_fkey"))
    private AccountSubtype accountSubtype;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "taxIdentity", referencedColumnName = "id", foreignKey = @ForeignKey(name = "account_tax_identity_fkey"), updatable = false)
    private TaxIdentity taxIdentity;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinTable(name = "accountContacts", joinColumns = {
            @JoinColumn(name = "accountId", referencedColumnName = "id",foreignKey = @ForeignKey(name = "account_contacts_account_fkey"))
    }, inverseJoinColumns = {
            @JoinColumn(name = "contactId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "account_contacts_contact_fkey"))
    })
    private Set<Contact> contacts = new HashSet<>();

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinTable(name = "accountsLineage", joinColumns = {
            @JoinColumn(name = "parentAccountId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "accounts_lineage_parent_acc_fkey"))
    }, inverseJoinColumns = {
            @JoinColumn(name = "childAccountId", referencedColumnName = "id",foreignKey = @ForeignKey(name = "accounts_lineage_child_acc_fkey"))
    })
    private Set<Account> childAccounts = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Account parentAccount;
}
