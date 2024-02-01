package com.ctytech.flierly.taxation.entity;

import com.ctytech.flierly.address.entity.Address;
import com.ctytech.flierly.organization.entity.Branch;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
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
@Getter @Setter @EqualsAndHashCode
@Table(name = "taxIdentities")
public class TaxIdentity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "branchId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "tax_identity_branch_fkey"), updatable = false)
    private Branch branch;

    @Column(length = 55, updatable = false)
    private String gst;

    private LocalDate gstRegistrationDate;

    private Boolean gstVerified;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "gstRegistrationAddress", referencedColumnName = "id", foreignKey = @ForeignKey(name = "tax_identity_gst_reg_address_fkey"))
    private Address gstRegistrationAddress;

    @Column(length = 30)
    private String pan;

    private  Boolean panVerified;

    @Column(length = 55)
    private String vat;

    @Column(length = 55)
    private String tin;
}
