package com.ctytech.flierly.account.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accountSubtypes")
@SequenceGenerator(name = "account_subtype_id_generator", sequenceName = "account_subtype_id_seq", initialValue = 1000, allocationSize = 1)
@Getter
@Setter
@EqualsAndHashCode
public class AccountSubtype {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_subtype_id_generator")
    private Long id;

    @NotBlank(message = "{account.subtype.name.absent}")
    @Pattern(regexp = "^[a-z0-9_]+$", message = "{account.subtype.name.invalid}")
    @Column(unique = true, nullable = false)
    private String name;

}
