package com.ctytech.flierly.address.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * <h1>PostalIdentity Entity<br/> <small> &nbsp tableName: postalIdentities</small> </h1>
 * <h3>Custom Indexes</h3>
 * <p>
 * This Entity will also have custom indexes other than auto generated indexes
 * This indexes will be created and maintained through native sql scripts
 * <br/>
 * <br/>
 * <h4>Partial Indexes</h4>
 * <p>Partial indexes on this entity for columns with null values will guarantees unique non-null values in the columns{"pinCode"} while allowing multiple nulls, optimizing queries and saving storage
 * </p>
 * <ul>
 *     <li>
 *         <p><b>idx_p_unique_gst</b></p>
 *         <p><b>Columns {"cityId", "pinCode"}</b></p>
 *         <p>Condition: If pinCode is not null</p>
 *     </li>
 * </ul>
 * </p>
 */
@Entity
@Table(name = "postalIdentities")
@SequenceGenerator(name = "postal_identity_id_generator", sequenceName = "postal_identity_id_seq", initialValue = 1000, allocationSize = 1)
@Getter
@Setter
@EqualsAndHashCode
public class PostalIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postal_identity_id_generator")
    private Long id;

    private Integer pinCode;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "cityId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "postal_identity_city_fkey"))
    private City city;

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "postalId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "postal_identity_areas_fkey"))
    private Set<Area> areas;
}
