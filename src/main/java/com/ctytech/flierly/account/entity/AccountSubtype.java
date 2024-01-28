package com.ctytech.flierly.account.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "accountSubtypes")
@Getter @Setter @EqualsAndHashCode
public class AccountSubtype implements Serializable {

    @Id
    @Column(nullable = false, updatable = false, length = 60)
    private String id;

    private String name;
}
