package com.ctytech.flierly.uom.service;

import com.ctytech.flierly.uom.dto.UomConversionDTO;
import com.ctytech.flierly.uom.exception.UomServiceException;

public interface UomConversionService {

    UomConversionDTO save(UomConversionDTO uomConversionDTO) throws UomServiceException;

    UomConversionDTO fetch(Long id) throws UomServiceException;

    UomConversionDTO fetch(String code) throws  UomServiceException;

    UomConversionDTO modify(Long id, UomConversionDTO update) throws UomServiceException;

}
