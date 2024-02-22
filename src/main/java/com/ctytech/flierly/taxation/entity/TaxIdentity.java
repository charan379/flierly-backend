package com.ctytech.flierly.taxation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <h1>TaxIdentity Entity<br/> <small> &nbsp tableName: taxIdentities</small> </h1>
 * <h3>Custom Indexes</h3>
 * <p>
 * This Entity will also have custom indexes other than auto generated indexes
 * This indexes will be created and maintained through native sql scripts
 * <br/>
 * <br/>
 * <h4>Partial Indexes</h4>
 * <p>Partial indexes on this entity for columns with null values will guarantees unique non-null values in the columns{"gst","pan","tin","vat"} while allowing multiple nulls, optimizing queries and saving storage
 * </p>
 * <ul>
 *     <li>
 *         <p><b>idx_p_unique_gst</b></p>
 *         <p><b>Columns {"gst"}</b></p>
 *         <p>Condition: If gst is not null</p>
 *     </li>
 *     <li>
 *         <p><b>idx_p_unique_pan</b></p>
 *         <p><b>Columns {"pan"}</b></p>
 *          <p>Condition: If pan is not null</p>
 *     </li>
 *     <li>
 *         <p><b>idx_p_unique_vat</b></p>
 *         <p><b>Columns {"vat"}</b></p>
 *         <p>Condition: If vat is not null</p>
 *     </li>
 *     <li>
 *         <p><b>idx_p_unique_tin</b></p>
 *         <p><b>Columns {"tin"}</b></p>
 *         <p>Condition: If tin is not null</p>
 *     </li>
 * </ul>
 * </p>
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "taxIdentities")
@SequenceGenerator(name = "tax_identity_id_generator", sequenceName = "tax_identity_id_seq", initialValue = 1000, allocationSize = 1)
public class TaxIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tax_identity_id_generator")
    private Long id;

    @Column(columnDefinition = "boolean default false")
    private Boolean isActive;

    @Size(min = 15, max = 15, message = "{taxIdentity.gst.invalid}")
    @Pattern(regexp = "^[0-9a-z]+$", message = "{gst.pattern.invalid}")
    @Column(length = 55)
    private String gst;

    @PastOrPresent(message = "{taxIdentity.gstRegDate.invalid}")
    private LocalDate gstRegistrationDate;

    @Column(columnDefinition = "boolean default false")
    private Boolean gstVerified;

    private Long gstRegistrationAddressId;

    @Size(min = 10, max = 10, message = "{taxIdentity.pan.invalid}")
    @Pattern(regexp = "^[0-9a-z]+$", message = "{pan.pattern.invalid}")
    @Column(length = 30)
    private String pan;

    @Column(columnDefinition = "boolean default false")
    private Boolean panVerified;

    @Column(length = 55)
    private String vat;

    @Column(length = 55)
    private String tin;
}
