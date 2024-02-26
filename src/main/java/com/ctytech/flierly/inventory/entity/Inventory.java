package com.ctytech.flierly.inventory.entity;

import com.ctytech.flierly.organization.entity.Branch;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "inventories")
@Getter @Setter @EqualsAndHashCode
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "branchId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "inventory_branch_fkey"))
    private Branch branch;
}
