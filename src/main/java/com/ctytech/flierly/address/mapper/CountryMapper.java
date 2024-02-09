package com.ctytech.flierly.address.mapper;

import com.ctytech.flierly.address.dto.CountryDTO;
import com.ctytech.flierly.address.entity.Country;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CountryDTO toDTO(Country country) {
        return modelMapper.map(country, CountryDTO.class);
    }

    public Country toEntity(CountryDTO countryDTO) {
        return modelMapper.map(countryDTO, Country.class);
    }
}
