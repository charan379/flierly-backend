package com.ctytech.flierly.address.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "districts", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nameCode", "stateId"})
})
@Getter @Setter @EqualsAndHashCode
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(updatable = false, length = 100)
    private String nameCode;

    @NotNull
    private String name;

    private Integer landlineCode;

    @NotNull
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "stateId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "district_state_fkey"))
    private State state;

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY, mappedBy = "district")
    private Set<City> cities = new HashSet<>();

}
