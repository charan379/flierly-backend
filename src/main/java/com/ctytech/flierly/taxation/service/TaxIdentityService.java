package com.ctytech.flierly.taxation.service;

import com.ctytech.flierly.taxation.dto.TaxIdentityDTO;
import com.ctytech.flierly.taxation.exception.TaxIdentityException;

public interface TaxIdentityService {

    TaxIdentityDTO save(TaxIdentityDTO taxIdentityDTO) throws TaxIdentityException;

    TaxIdentityDTO fetch(Long id) throws TaxIdentityException;

    TaxIdentityDTO fetchByGstNumber(String gstNumber) throws TaxIdentityException;

    TaxIdentityDTO fetchByPanNumber(String panNumber) throws TaxIdentityException;

    Boolean existsByGst(String gstNumber);

    Boolean existsByPan(String panNumber);
}
