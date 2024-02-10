package com.ctytech.flierly.address.service;

import com.ctytech.flierly.address.dto.CountryDTO;
import com.ctytech.flierly.address.exception.CountryServiceException;

public interface CountryService {

    CountryDTO save(CountryDTO countryDTO) throws CountryServiceException;

    CountryDTO fetch(Long id) throws CountryServiceException;

    CountryDTO fetch(String code) throws CountryServiceException;

    CountryDTO modify(Long id, CountryDTO update) throws CountryServiceException;

    void remove(Long id) throws CountryServiceException;

    Boolean existsByCode(String code) throws CountryServiceException;
}
