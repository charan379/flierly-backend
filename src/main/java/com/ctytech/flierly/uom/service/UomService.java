package com.ctytech.flierly.uom.service;

import com.ctytech.flierly.uom.dto.UomDTO;
import com.ctytech.flierly.uom.dto.UomPageRequestDTO;
import com.ctytech.flierly.uom.exception.UomServiceException;
import org.springframework.data.domain.Page;

public interface UomService {

    UomDTO save(UomDTO uomDTO) throws UomServiceException;

    UomDTO fetch(Long id) throws UomServiceException;

    UomDTO fetch(String code) throws UomServiceException;

    Page<UomDTO> fetchPage(UomPageRequestDTO uomPageRequestDTO) throws UomServiceException;

    UomDTO modify(Long id, UomDTO update) throws UomServiceException;

    void remove(Long id) throws UomServiceException;

    Boolean codeExists(String code) throws UomServiceException;
}
