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
        if (district == null) return null;
        return modelMapper.map(district, DistrictDTO.class);
    }

    public DistrictDTO toDTO(District district, boolean isMinimal) {
        if (district == null) return null;
        if (isMinimal) {
            DistrictDTO districtDTO = new DistrictDTO();
            districtDTO.setId(district.getId());
            districtDTO.setCode(district.getCode());
            districtDTO.setName(district.getName());
            districtDTO.setLandlineCode(district.getLandlineCode());
            return districtDTO;
        }
        return modelMapper.map(district, DistrictDTO.class);
    }

    public District toEntity(DistrictDTO districtDTO) {
        return modelMapper.map(districtDTO, District.class);
    }
}
