package com.ctytech.flierly.address.service;

import com.ctytech.flierly.address.dto.DistrictDTO;
import com.ctytech.flierly.address.exception.DistrictServiceException;

import java.util.List;

public interface DistrictService {

    DistrictDTO save(DistrictDTO districtDTO) throws DistrictServiceException;

    DistrictDTO fetch(Long id) throws DistrictServiceException;

    DistrictDTO fetch(String code, Long stateId) throws DistrictServiceException;

    List<DistrictDTO> fetchAllByStateId(Long stateId) throws DistrictServiceException;

    DistrictDTO modify(Long id, DistrictDTO update) throws DistrictServiceException;

    void remove(Long id) throws DistrictServiceException;

    Boolean exitsByCodeAndStateId(String code, Long stateId);

    Boolean existsByLandlineCodeAndStateId(Integer landlineCode, Long stateId);
}
