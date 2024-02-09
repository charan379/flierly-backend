package com.ctytech.flierly.address.mapper;

import com.ctytech.flierly.address.dto.DistrictDTO;
import com.ctytech.flierly.address.entity.District;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DistrictMapper {

    @Autowired
    private ModelMapper modelMapper;

    public DistrictDTO toDTO(District district) {
        return modelMapper.map(district, DistrictDTO.class);
    }

    public District toEntity(DistrictDTO districtDTO) {
        return  modelMapper.map(districtDTO, District.class);
    }
}
