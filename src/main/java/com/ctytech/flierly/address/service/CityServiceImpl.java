package com.ctytech.flierly.address.service;

import com.ctytech.flierly.address.dto.CityDTO;
import com.ctytech.flierly.address.dto.DistrictDTO;
import com.ctytech.flierly.address.entity.City;
import com.ctytech.flierly.address.exception.CityServiceException;
import com.ctytech.flierly.address.mapper.CityMapper;
import com.ctytech.flierly.address.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "cityService")
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityMapper cityMapper;

    @Override
    public CityDTO save(CityDTO cityDTO) throws CityServiceException {
        Long districtId = Optional.ofNullable(cityDTO.getDistrict()).map(DistrictDTO::getId).orElseThrow(() -> new CityServiceException("CityService.CITY_DISTRICT_ID_ABSENT"));

        if (existsByCodeAndDistrictId(cityDTO.getCode(), districtId))
            throw new CityServiceException("CityService.CODE_ALREADY_EXISTS");

        City city = cityMapper.toEntity(cityDTO);

        return cityMapper.toDTO(cityRepository.save(city), true);
    }

    @Override
    public CityDTO fetch(Long id) throws CityServiceException {
        City city = cityRepository.findById(id).orElseThrow(() -> new CityServiceException("CityService.NOT_FOUND"));
        return cityMapper.toDTO(city);
    }

    @Override
    public CityDTO fetch(String code, Long districtId) throws CityServiceException {
        City city = cityRepository.findByCodeAndDistrictId(code, districtId).orElseThrow(() -> new CityServiceException("CityService.NOT_FOUND"));
        return cityMapper.toDTO(city, true);
    }

    @Override
    public List<CityDTO> fetchAllByDistrictId(Long districtId) {
        return cityRepository.findAllByDistrictId(districtId).stream().map(city -> cityMapper.toDTO(city, true)).toList();
    }

    @Override
    public CityDTO modify(Long id, CityDTO update) throws CityServiceException {
        City city = cityRepository.findById(id).orElseThrow(() -> new CityServiceException("CityService.NOT_FOUND"));

        if (city.getName().equals(update.getName())) return cityMapper.toDTO(city);

        city.setName(update.getName());

        return cityMapper.toDTO(cityRepository.save(city), true);
    }

    @Override
    public void remove(Long id) throws CityServiceException {

    }

    @Override
    public Boolean existsByCodeAndDistrictId(String code, Long districtId) {
        return cityRepository.existsByCodeAndDistrictId(code, districtId);
    }
}
