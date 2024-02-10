package com.ctytech.flierly.address.service;

import com.ctytech.flierly.address.dto.CityDTO;
import com.ctytech.flierly.address.exception.CityServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "cityService")
public class CityServiceImpl implements CityService {
    @Override
    public CityDTO save(CityDTO cityDTO) throws CityServiceException {
        return null;
    }

    @Override
    public CityDTO fetch(Long id) throws CityServiceException {
        return null;
    }

    @Override
    public List<CityDTO> fetchAllByDistrictId(Long DistrictId) throws CityServiceException {
        return null;
    }

    @Override
    public CityDTO modify(Long id, CityDTO update) throws CityServiceException {
        return null;
    }

    @Override
    public void remove(Long id) throws CityServiceException {

    }
}
