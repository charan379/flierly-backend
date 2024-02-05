package com.ctytech.flierly.uom.service;

import com.ctytech.flierly.uom.dto.UomDTO;
import com.ctytech.flierly.uom.exception.UomServiceException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service(value = "uomService")
@Transactional
public class UomServiceImpl implements UomService{
    @Override
    public UomDTO save(UomDTO uomDTO) throws UomServiceException {
        return null;
    }

    @Override
    public UomDTO fetchById(Long id) throws UomServiceException {
        return null;
    }

    @Override
    public UomDTO fetchByCode(String code) throws UomServiceException {
        return null;
    }

    @Override
    public UomDTO modifyById(Long id, UomDTO update) throws UomServiceException {
        return null;
    }

    @Override
    public void removeById(Long id) throws UomServiceException {

    }

    @Override
    public Boolean existsByCode(String code) throws UomServiceException {
        return null;
    }

    @Override
    public Page<UomDTO> fetchByQuery(String codeQ, String nameQ, Integer pageNo, Integer resultsPerPage, String sort) throws UomServiceException {
        return null;
    }
}
