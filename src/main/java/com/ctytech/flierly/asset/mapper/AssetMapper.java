package com.ctytech.flierly.asset.mapper;

import com.ctytech.flierly.asset.dto.AssetDTO;
import com.ctytech.flierly.asset.entity.Asset;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssetMapper {

    @Autowired
    private ModelMapper modelMapper;

    public AssetDTO toDTO(Asset asset) {
        return modelMapper.map(asset, AssetDTO.class);
    }

    public Asset toEntity(AssetDTO assetDTO) {
        return  modelMapper.map(assetDTO, Asset.class);
    }
}
