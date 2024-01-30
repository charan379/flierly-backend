package com.ctytech.flierly.address.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "districtId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "city_district_fkey"))
    private District district;

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY, mappedBy = "city")
    private Set<PostalIdentity> postalIdentities = new HashSet<PostalIdentity>();

}
