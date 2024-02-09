package com.ctytech.flierly.address.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "countries")
@SequenceGenerator(name = "country_id_generator", sequenceName = "country_id_seq", initialValue = 1000, allocationSize = 1)
@Getter
@Setter
@EqualsAndHashCode
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_id_generator")
    private Long id;

    @NotBlank(message = "{country.code.absent}")
    @Size(max = 100, message = "{code.size.invalid}")
    @Pattern(regexp = "^[a-z_]+$", message = "{code.pattern.invalid}")
    @Column(length = 100, unique = true, updatable = false)
    private String code;

    @NotBlank(message = "{country.name.absent}")
    private String name;

    @NotNull(message = "{country.dialingCode.absent}")
    @Column(unique = true)
    private Integer dialingCode;

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY, mappedBy = "country")
    private Set<State> states;
}
