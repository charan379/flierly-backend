package com.ctytech.flierly.account.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accountTypes")
@Getter @Setter @EqualsAndHashCode
public class AccountType implements Serializable {

    @Id
    @Column(nullable = false, updatable = false, length = 60)
    private String id;

    @NotNull
    private String name;

    @NotNull
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "accountTypeSubtypeAssociations",joinColumns = {
            @JoinColumn(name = "accountType", referencedColumnName = "id")
    },inverseJoinColumns =  {
            @JoinColumn(name = "accountSubtype", referencedColumnName = "id")
    })
    private Set<AccountSubtype> accountSubtypes = new HashSet<>();
}
