package com.ctytech.flierly.uom.service;

import com.ctytech.flierly.uom.dto.UomDTO;
import com.ctytech.flierly.uom.dto.UomPageRequestDTO;
import com.ctytech.flierly.uom.exception.UomServiceException;
import com.ctytech.flierly.uom.repository.UomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "uomService")
@Transactional
public class UomServiceImpl implements UomService {

    @Autowired
    private UomRepository uomRepository;

    @Override
    public UomDTO save(UomDTO uomDTO) throws UomServiceException {
        return null;
    }

    @Override
    public UomDTO fetch(Long id) throws UomServiceException {
        return null;
    }

    @Override
    public UomDTO fetch(String code) throws UomServiceException {
        return null;
    }

    @Override
    public Page<UomDTO> fetchPage(UomPageRequestDTO uomPageRequestDTO) throws UomServiceException {
        return null;
    }

    @Override
    public UomDTO modify(Long id, UomDTO update) throws UomServiceException {
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
