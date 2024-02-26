package com.ctytech.flierly.address.service;

import com.ctytech.flierly.address.dto.StateDTO;
import com.ctytech.flierly.address.exception.StateServiceException;

import java.util.List;

public interface StateService {

    StateDTO save(StateDTO stateDTO) throws StateServiceException;

    StateDTO fetch(Long id) throws StateServiceException;

    StateDTO fetch(Long countryId, String code) throws StateServiceException;

    List<StateDTO> fetchAllByCountryId(Long countryId) throws StateServiceException;

    StateDTO modify(Long id, StateDTO update) throws StateServiceException;

    void remove(Long id) throws StateServiceException;

    Boolean existsByCountryIdAndGstCode(Long countryId, Integer gstCode) throws StateServiceException;

    Boolean exitsByCountryIdAndCode(Long countryId, String code) throws StateServiceException;
}
