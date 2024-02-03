package com.ctytech.flierly.product.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "productAttributeSchemas")
@Getter @Setter @EqualsAndHashCode
public class ProductAttributeSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(updatable = false, length = 65, unique = true)
    private String nameCode;

    @NotNull
    private String displayName;

    private Boolean isRequired;

    @NotNull
    private String regex;

    @NotNull
    private String validationMessage;
}
