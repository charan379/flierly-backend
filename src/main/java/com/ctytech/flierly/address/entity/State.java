package com.ctytech.flierly.address.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity()
@Table(name = "states", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"gstCode", "countryId"}),
        @UniqueConstraint(columnNames = {"nameCode", "countryId"})
})
@Getter @Setter @EqualsAndHashCode
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(updatable = false, length = 100)
    private String nameCode;

    @NotNull
    private String displayName;

    private Boolean isUnionTerritory;

    @Column(updatable = false)
    private Integer gstCode;

    @ManyToOne(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    @JoinColumn(name = "countryId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "state_and_country_fkey"), updatable = false)
    private Country country;

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY, mappedBy = "state")
    private Set<District> districts = new HashSet<District>();
}
