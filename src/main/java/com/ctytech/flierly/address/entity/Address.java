package com.ctytech.flierly.address.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "addresses")
@Getter @Setter @EqualsAndHashCode
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Boolean isPrimary;

    @NotNull
    private String line1;

    private String line2;

    private  String line3;

    @NotNull
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "countryId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "address_country_fkey"))
    private Country country;

    @NotNull
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "stateId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "address_state_fkey"))
    private State state;

    @NotNull
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "districtId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "address_district_fkey"))
    private District district;

    @NotNull
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "cityId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "address_city_fkey"))
    private City city;

    @NotNull
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "postalId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "address_postal_identity_fkey"))
    private PostalIdentity postalIdentity;

    @NotNull
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "areaId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "address_area_fkey"))
    private Area area;

    private String landMark;

    private Double latitude;

    private Double longitude;
}
