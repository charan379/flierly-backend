package com.ctytech.flierly.address.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;

@Entity
@Table(name = "addresses")
@SequenceGenerator(name = "address_id_generator", sequenceName = "address_id_seq", initialValue = 1000, allocationSize = 1)
@Getter
@Setter
@EqualsAndHashCode
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_generator")
    private Long id;

    @Column(columnDefinition = "boolean default false")
    private Boolean isActive;

    @Column(columnDefinition = "boolean default false")
    private Boolean isPrimary;

    @NotBlank(message = "{address.line1.absent}")
    private String line1;

    private String line2;

    private String line3;

    @NotNull(message = "{address.country.absent}")
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "countryId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "address_country_fkey"))
    private Country country;

    @NotNull(message = "{address.state.absent}")
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "stateId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "address_state_fkey"))
    private State state;

    @NotNull(message = "{address.district.absent}")
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "districtId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "address_district_fkey"))
    private District district;

    @NotNull(message = "{address.city.absent}")
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "cityId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "address_city_fkey"))
    private City city;

    @NotNull(message = "{address.postalIdentity.absent}")
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "postalId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "address_postal_identity_fkey"))
    private PostalIdentity postalIdentity;

    @NotNull(message = "{address.area.absent}")
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "areaId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "address_area_fkey"))
    private Area area;

    private String landMark;

    @Column(columnDefinition = "numeric(10,7)")
    private BigDecimal latitude;

    @Column(columnDefinition = "numeric(10,7)")
    private BigDecimal longitude;
}
