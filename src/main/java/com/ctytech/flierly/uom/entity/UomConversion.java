package com.ctytech.flierly.uom.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "uomConversions")
@Getter @Setter @EqualsAndHashCode
public class UomConversion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true, length = 100)
    private String code;

    @NotNull
    private String name;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fromUomId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "uom_conversion_from_uom_fkey"))
    private UnitOfMeasurement fromUom;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "toUomId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "uom_conversion_to_uom_fkey"))
    private UnitOfMeasurement toUom;

    @Column(columnDefinition = "numeric(10,2) default 1.0")
    private BigDecimal conversionFactor;
}
