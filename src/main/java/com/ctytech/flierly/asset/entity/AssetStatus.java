package com.ctytech.flierly.asset.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "assetStatuses")
@Getter @Setter @EqualsAndHashCode
public class AssetStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 65, nullable = true, unique = true)
    private String code;

    @NotNull
    private String name;

    private String description;
}
