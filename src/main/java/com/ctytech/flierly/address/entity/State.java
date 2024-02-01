package com.ctytech.flierly.address.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * <h1>State Entity<br/> <small> &nbsp table_name: states</small> </h1>
 * <h3>Custom Indexes</h3>
 * <p>
 * This Entity will also have custom indexes other than auto generated indexes
 * This indexes will be created and maintained through native sql scripts
 * <br/>
 * <br/>
 * <h4>Partial Indexes</h4>
 * <p>Partial indexes on this entity for columns with null values will guarantees unique non-null values in the columns{"gstCode"} while allowing multiple nulls, optimizing queries and saving storage
 * </p>
 * <ul>
 *     <li>
 *         <p><b>idx_p_unique_country_and_gst_code</b></p>
 *         <p><b>Columns {"countryId", "gstCode"}</b></p>
 *         <p>Condition: If gstCode is not null</p>
 *     </li>
 * </ul>
 * </p>
 */
@Entity()
@Table(name = "states", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nameCode", "countryId"})
})
@Getter @Setter @EqualsAndHashCode
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(updatable = false, length = 100)
    private String nameCode;

    @NotNull
    private String displayName;

    private Boolean isUnionTerritory;

    @Column(updatable = false)
    private Integer gstCode;

    @NotNull
    @ManyToOne(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    @JoinColumn(name = "countryId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "state_country_fkey"), updatable = false)
    private Country country;

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY, mappedBy = "state")
    private Set<District> districts = new HashSet<>();
}
