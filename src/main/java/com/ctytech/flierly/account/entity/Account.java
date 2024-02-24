package com.ctytech.flierly.account.entity;

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

    @NotNull(message = "{account.type.absent}")
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "accountType", referencedColumnName = "id", foreignKey = @ForeignKey(name = "account_account_type_fkey"))
    private AccountType accountType;

    @NotNull(message = "{account.subtype.absent}")
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "accountSubtype", referencedColumnName = "id", foreignKey = @ForeignKey(name = "account_account_subtype_fkey"))
    private AccountSubtype accountSubtype;

    @NotNull(message = "{account.branch.absent}")
    private Long branchId;

    private Long taxIdentityId;

    @ElementCollection(targetClass = Long.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "account_contacts")
    @Column(name = "contact_id", nullable = false)
    private Set<Long> contactIds = new HashSet<>();

    @ElementCollection(targetClass = Long.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "account_addresses")
    @Column(name = "address_id", nullable = false)
    private Set<Long> addressIds = new HashSet<>();

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY, mappedBy = "parentAccount")
    private Set<Account> childAccounts = new HashSet<>();

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "accountsLineage", joinColumns = {@JoinColumn(name = "parentAccountId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "accounts_lineage_parent_acc_fkey"))}, inverseJoinColumns = {@JoinColumn(name = "childAccountId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "accounts_lineage_child_acc_fkey"))})
    private Account parentAccount;
}
