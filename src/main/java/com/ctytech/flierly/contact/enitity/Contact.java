package com.ctytech.flierly.contact.enitity;

import com.ctytech.flierly.organization.entity.Branch;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contacts")
@Getter @Setter @EqualsAndHashCode
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "branchId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "contact_branch_fkey"), updatable = false)
    private Branch branch;

    @NotNull
    private String name;
}
