package com.ctytech.flierly.address.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "postalIdentities", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"pincode", "cityId"})
})
public class PostalIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer pinCode;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "cityId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "postal_identity_and_city_fkey" ))
    private City city;

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "postalId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "postal_identity_areas_fkey"))
    private Set<Area> areas = new HashSet<Area>();
}
