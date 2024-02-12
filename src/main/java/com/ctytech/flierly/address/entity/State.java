package com.ctytech.flierly.address.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


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
@Entity
@Table(name = "states", uniqueConstraints = {@UniqueConstraint(columnNames = {"code", "countryId"})})
@SequenceGenerator(name = "state_id_generator", sequenceName = "state_id_seq", initialValue = 1000, allocationSize = 1)
@Getter
@Setter
@EqualsAndHashCode
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "state_id_generator")
    private Long id;

    @NotBlank(message = "{state.code.absent}")
    @Size(max = 100, message = "{code.size.invalid}")
    @Pattern(regexp = "^[a-z_]+$", message = "{code.pattern.invalid}")
    @Column(updatable = false, length = 100)
    private String code;

    @NotEmpty(message = "{state.name.absent}")
    private String name;

    @Column(columnDefinition = "boolean default false")
    private Boolean isUnionTerritory;

    // Only updatable if its null, once GstCode Persist it should not be updated.
    private Integer gstCode;

    @NotNull(message = "{state.country.absent}")
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "countryId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "state_country_fkey"), updatable = false)
    private Country country;
}
