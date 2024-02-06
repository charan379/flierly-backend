package com.ctytech.flierly.uom.service;

import com.ctytech.flierly.uom.dto.UomConversionDTO;
import com.ctytech.flierly.uom.dto.UomConversionPageRequestDTO;
import com.ctytech.flierly.uom.exception.UomServiceException;
import com.ctytech.flierly.uom.repository.UomConversionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "UomConversionService")
@Transactional
public class UomConversionServiceImpl implements UomConversionService {

    @Autowired
    private UomConversionsRepository uomConversionsRepository;

    @Override
    public UomConversionDTO save(UomConversionDTO uomConversionDTO) throws UomServiceException {
        return null;
    }

    @Override
    public UomConversionDTO fetch(Long id) throws UomServiceException {
        return null;
    }

    @Override
    public UomConversionDTO fetch(String code) throws UomServiceException {
        return null;
    }

    @Override
    public Page<UomConversionDTO> fetchPage(UomConversionPageRequestDTO uomConversionPageRequestDTO) throws UomServiceException {
        return null;
    }

    @Override
    public UomConversionDTO modify(Long id, UomConversionDTO update) throws UomServiceException {
        return null;
    }

    @Override
    public void remove(Long id) throws UomServiceException {

    }

    @Override
    public Boolean codeExists(String code) throws UomServiceException {
        return null;
    }
}
