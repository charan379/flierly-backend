package com.ctytech.flierly.uom.mapper;

import com.ctytech.flierly.uom.dto.UomConversionDTO;
import com.ctytech.flierly.uom.entity.UomConversion;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UomConversionMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UomConversionDTO toDTO(UomConversion uomConversion) {
        return modelMapper.map(uomConversion, UomConversionDTO.class);
    }

    public UomConversion toEntity(UomConversionDTO uomConversionDTO) {
        return modelMapper.map(uomConversionDTO, UomConversion.class);
    }
}
