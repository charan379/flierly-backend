package com.ctytech.flierly.address.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "addresses")
@Getter @Setter
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, columnDefinition = "default 'n'")
    private Boolean isPrimary;

    @NotNull
    private String line1;

    private String line2;

    private  String line3;

    @NotNull
    private String country;
    @NotNull
    private String state;

    @NotNull
    private String district;

    @NotNull
    private Long pincode;

    @NotNull
    private String area;

    private String landMark;

    private Double latitude;

    private Double longitude;
}
