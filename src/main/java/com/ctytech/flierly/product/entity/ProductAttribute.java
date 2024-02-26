package com.ctytech.flierly.product.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "productAttributes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nameCode", "productId"}, name = "product_attribute_name_code_product_id_ukey")
})
@Getter @Setter @EqualsAndHashCode
public class ProductAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(updatable = false, length = 65)
    private String nameCode;

    @NotNull
    private String displayName;

    @NotNull
    private String value;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "productId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "product_attribute_product_fkey"))
    private Product product;
}
