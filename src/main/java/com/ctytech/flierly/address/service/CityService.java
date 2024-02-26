package com.ctytech.flierly.address.service;

import com.ctytech.flierly.address.dto.CityDTO;
import com.ctytech.flierly.address.exception.CityServiceException;

import java.util.List;

public interface CityService {

    CityDTO save(CityDTO cityDTO) throws CityServiceException;

    CityDTO fetch(Long id) throws CityServiceException;

    CityDTO fetch(String code, Long districtId) throws CityServiceException;

    List<CityDTO> fetchAllByDistrictId(Long districtId);

    CityDTO modify(Long id, CityDTO update) throws CityServiceException;

    void remove(Long id) throws CityServiceException;

    Boolean existsByCodeAndDistrictId(String code, Long districtId);

}
