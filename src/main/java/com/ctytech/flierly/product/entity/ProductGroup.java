package com.ctytech.flierly.product.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "productGroups", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nameCode", "categoryId"}, name = "product_group_name_code_category_ukey")
})
@Getter @Setter @EqualsAndHashCode
public class ProductGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(updatable = false, length = 65)
    private String nameCode;

    @NotNull
    private String displayName;

    @NotNull
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "product_group_category_fkey"))
    private ProductCategory productCategory;

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY, mappedBy = "productGroup")
    private Set<ProductSubgroup> productSubgroups = new HashSet<>();
}
