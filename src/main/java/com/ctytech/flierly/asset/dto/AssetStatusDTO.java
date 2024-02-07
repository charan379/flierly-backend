package com.ctytech.flierly.asset.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter @EqualsAndHashCode
public class AssetStatusDTO implements Serializable {

    private Long id;

    private String code;

    private String name;

    private String description;
}
