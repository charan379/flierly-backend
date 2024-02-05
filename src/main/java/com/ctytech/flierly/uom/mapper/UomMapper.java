package com.ctytech.flierly.uom.mapper;

import com.ctytech.flierly.uom.dto.UomDTO;
import com.ctytech.flierly.uom.entity.Uom;
import lombok.EqualsAndHashCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UomMapper {

    @Autowired
    private ModelMapper modelMapper;


    public UomDTO toDTO(Uom uom) {
        return modelMapper.map(uom, UomDTO.class);
    }

    public Uom toEntity(UomDTO uomDTO) {
        return modelMapper.map(uomDTO,Uom.class);
    }
}
