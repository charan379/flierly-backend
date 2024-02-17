package com.ctytech.flierly.account.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accountTypes")
@SequenceGenerator(name = "account_type_id_generator", sequenceName = "account_type_id_seq", initialValue = 1000, allocationSize = 1)
@Getter
@Setter
@EqualsAndHashCode
public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_type_id_generator")
    private Long id;

    @NotBlank(message = "account.type.name.absent")
    @Pattern(regexp = "^[a-z0-9_]+$", message = "account.type.name.invalid")
    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "accountTypesLineage", joinColumns = {@JoinColumn(name = "accountType", referencedColumnName = "id", foreignKey = @ForeignKey(name = "acc_type_lineage_acc_type_fkey"))}, inverseJoinColumns = {@JoinColumn(name = "accountSubtype", referencedColumnName = "id", foreignKey = @ForeignKey(name = "acc_type_lineage_acc_subtype_fkey"))})
    private Set<AccountSubtype> subtypes = new HashSet<>();
}
