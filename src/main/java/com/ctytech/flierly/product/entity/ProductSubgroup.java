package com.ctytech.flierly.product.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ProductSubgroups", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nameCode", "groupId"}, name = "product_subgroup_group_ukey")
})
@Getter @Setter @EqualsAndHashCode
public class ProductSubgroup {

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
    @JoinColumn(name = "groupId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "product_subgroup_group_fkey"))
    private ProductGroup productGroup;
}
