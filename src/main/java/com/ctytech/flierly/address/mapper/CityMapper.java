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
        return modelMapper.map(city, CityDTO.class);
    }

    public City toEntity(CityDTO cityDTO) {
        return modelMapper.map(cityDTO, City.class);
    }

}
