package com.ctytech.flierly.uom.service;

import com.ctytech.flierly.uom.dto.UomDTO;
import com.ctytech.flierly.uom.exception.UomServiceException;
import org.springframework.data.domain.Page;

public interface UomService {

    UomDTO save(UomDTO uomDTO) throws UomServiceException;

    UomDTO fetchById(Long id) throws UomServiceException;

    UomDTO fetchByCode(String code) throws UomServiceException;

    UomDTO modifyById(Long id, UomDTO update) throws UomServiceException;

    void removeById(Long id) throws UomServiceException;

    Boolean existsByCode(String code) throws UomServiceException;

    Page<UomDTO> fetchByQuery(String codeQ, String nameQ, Integer pageNo, Integer resultsPerPage, String sort) throws UomServiceException;
}
