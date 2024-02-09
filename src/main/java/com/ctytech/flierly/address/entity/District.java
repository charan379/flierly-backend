package com.ctytech.flierly.address.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "districts", uniqueConstraints = {@UniqueConstraint(columnNames = {"code", "stateId"})})
@SequenceGenerator(name = "district_id_generator", sequenceName = "district_id_seq", initialValue = 1000, allocationSize = 1)
@Getter
@Setter
@EqualsAndHashCode
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "district_id_generator")
    private Long id;

    @NotBlank(message = "{district.code.absent}")
    @Size(max = 100, message = "{code.size.invalid}")
    @Pattern(regexp = "^[a-z_]+$", message = "{code.pattern.invalid}")
    @Column(updatable = false, length = 100)
    private String code;

    @NotBlank(message = "{district.name.absent}")
    private String name;

    private Integer landlineCode;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "stateId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "district_state_fkey"))
    private State state;

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY, mappedBy = "district")
    private Set<City> cities;

}
