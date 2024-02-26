package com.ctytech.flierly.uom.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "uoms")
@Getter @Setter @EqualsAndHashCode
public class Uom {

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
