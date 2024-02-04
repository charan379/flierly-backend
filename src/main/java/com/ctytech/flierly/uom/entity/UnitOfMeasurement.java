package com.ctytech.flierly.uom.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "unitOfMeasurements")
@Getter @Setter @EqualsAndHashCode
public class UnitOfMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @NotNull
    @Column(nullable = false)
    private String name;
}
