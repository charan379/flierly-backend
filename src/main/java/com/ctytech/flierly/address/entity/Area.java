package com.ctytech.flierly.address.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "areas")
@SequenceGenerator(name = "area_id_generator", sequenceName = "area_id_seq", initialValue = 1000, allocationSize = 1)
@Getter
@Setter
@EqualsAndHashCode
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "area_id_generator")
    private Long id;

    @NotBlank(message = "{area.name.absent}")
    private String name;
}
