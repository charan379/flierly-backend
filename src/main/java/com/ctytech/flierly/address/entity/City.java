package com.ctytech.flierly.address.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cities")
@SequenceGenerator(name = "city_id_generator", sequenceName = "city_id_seq", initialValue = 1000, allocationSize = 1)
@Getter
@Setter
@EqualsAndHashCode
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_id_generator")
    private Long id;

    @NotBlank(message = "{city.code.absent}")
    @Size(max = 100, message = "{code.size.invalid}")
    @Pattern(regexp = "^[a-z_]+$", message = "{code.pattern.invalid}")
    @Column(updatable = false, length = 100)
    private String code;

    @NotBlank(message = "{city.name.absent}")
    private String name;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "districtId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "city_district_fkey"))
    private District district;
}
