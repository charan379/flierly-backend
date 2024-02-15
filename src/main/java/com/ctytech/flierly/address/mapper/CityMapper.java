package com.ctytech.flierly.address.mapper;

import com.ctytech.flierly.address.dto.CityDTO;
import com.ctytech.flierly.address.entity.City;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CityDTO toDTO(City city) {
        if (city == null) return null;
        return modelMapper.map(city, CityDTO.class);
    }

    public CityDTO toDTO(City city, boolean isMinimal) {
        if (city == null) return null;
        if (isMinimal) {
            CityDTO cityDTO = new CityDTO();
            cityDTO.setId(city.getId());
            cityDTO.setCode(city.getCode());
            cityDTO.setName(city.getName());
            return cityDTO;
        }
        return modelMapper.map(city, CityDTO.class);
    }

    public City toEntity(CityDTO cityDTO) {
        return modelMapper.map(cityDTO, City.class);
    }

}
