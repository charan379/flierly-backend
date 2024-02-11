package com.ctytech.flierly.address.service;

import com.ctytech.flierly.address.dto.StateDTO;
import com.ctytech.flierly.address.exception.StateServiceException;

import java.util.List;

public interface StateService {

    StateDTO save(StateDTO stateDTO) throws StateServiceException;

    StateDTO fetch(Long id) throws StateServiceException;

    List<StateDTO> fetchByCountryId(Long id) throws StateServiceException;

    StateDTO modify(Long id, StateDTO update) throws StateServiceException;

    void remove(Long id) throws StateServiceException;

    Boolean existsByGstCodeAndCountryId(Integer gstCode, Long countryId) throws StateServiceException;

    Boolean exitsByCodeAndCountryId(String code, Long countryId) throws StateServiceException;
}
