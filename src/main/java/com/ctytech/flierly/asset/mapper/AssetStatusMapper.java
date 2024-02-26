package com.ctytech.flierly.asset.mapper;

import com.ctytech.flierly.asset.dto.AssetStatusDTO;
import com.ctytech.flierly.asset.entity.AssetStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssetStatusMapper {

    @Autowired
    private ModelMapper modelMapper;

    public AssetStatusDTO toDTO(AssetStatus assetStatus) {
        return modelMapper.map(assetStatus, AssetStatusDTO.class);
    }

    public  AssetStatus toEntity(AssetStatusDTO assetStatusDTO) {
        return modelMapper.map(assetStatusDTO, AssetStatus.class);
    }

}
