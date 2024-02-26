package com.ctytech.flierly.uom.service;

import com.ctytech.flierly.uom.dto.UomConversionDTO;
import com.ctytech.flierly.uom.dto.UomConversionPageRequestDTO;
import com.ctytech.flierly.uom.exception.UomServiceException;
import org.springframework.data.domain.Page;

public interface UomConversionService {

    UomConversionDTO save(UomConversionDTO uomConversionDTO) throws UomServiceException;

    UomConversionDTO fetch(Long id) throws UomServiceException;

    UomConversionDTO fetch(String code) throws UomServiceException;

    Page<UomConversionDTO> fetchPage(UomConversionPageRequestDTO uomConversionPageRequestDTO) throws UomServiceException;

    UomConversionDTO modify(Long id, UomConversionDTO update) throws UomServiceException;

    void remove(Long id) throws UomServiceException;

    Boolean codeExists(String code) throws UomServiceException;
}
