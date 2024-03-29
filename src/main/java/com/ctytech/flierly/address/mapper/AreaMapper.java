package com.ctytech.flierly.address.mapper;

import com.ctytech.flierly.address.dto.AreaDTO;
import com.ctytech.flierly.address.entity.Area;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AreaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public AreaDTO toDTO(Area area) {
        if (area == null) return null;
        return modelMapper.map(area, AreaDTO.class);
    }

    public AreaDTO toDTO(Area area, boolean isMinimal) {
        if (area == null) return null;
        if (isMinimal) {
            AreaDTO areaDTO = new AreaDTO();
            areaDTO.setId(area.getId());
            areaDTO.setName(area.getName());
            return areaDTO;
        }
        return modelMapper.map(area, AreaDTO.class);
    }

    public Area toEntity(AreaDTO areaDTO) {
        return modelMapper.map(areaDTO, Area.class);
    }
}
