package com.ctytech.flierly.product.entity;

import com.ctytech.flierly.organization.entity.Branch;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter @Setter @EqualsAndHashCode
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "branchId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "product_branch_fkey"))
    private Branch branch;

    @NotNull
    @Column(unique = true)
    private String name;

    @Column(columnDefinition = "boolean default false")
    private Boolean isSerialized;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private Set<ProductAttribute> productAttributes = new HashSet<>();

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinTable(name = "productAttributeSchemaMappings", joinColumns = {
            @JoinColumn(name = "productId", referencedColumnName = "id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "attributeSchemaId", referencedColumnName = "id")
    })
    private Set<ProductAttributeSchema> productAttributeSchemas = new HashSet<>();
}
