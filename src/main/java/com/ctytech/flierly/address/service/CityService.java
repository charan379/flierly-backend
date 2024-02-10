package com.ctytech.flierly.address.service;

import com.ctytech.flierly.address.dto.CityDTO;
import com.ctytech.flierly.address.exception.CityServiceException;

import java.util.List;

public interface CityService {

    CityDTO save(CityDTO cityDTO) throws CityServiceException;

    CityDTO fetch(Long id) throws CityServiceException;

    List<CityDTO> fetchAllByDistrictId(Long DistrictId) throws CityServiceException;

    CityDTO modify(Long id, CityDTO update) throws CityServiceException;

    void remove(Long id) throws CityServiceException;
}
