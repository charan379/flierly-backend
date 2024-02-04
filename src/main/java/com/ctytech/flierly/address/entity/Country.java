package com.ctytech.flierly.address.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
@Getter @Setter @EqualsAndHashCode
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 100, unique =true, updatable = false)
    private String nameCode;

    @NotNull
    private String name;

    @NotNull
    @Column(unique = true)
    private Integer dialingCode;

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY, mappedBy = "country")
    private Set<State> states = new HashSet<>();
}
