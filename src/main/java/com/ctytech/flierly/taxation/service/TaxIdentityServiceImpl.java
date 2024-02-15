package com.ctytech.flierly.taxation.service;

import com.ctytech.flierly.taxation.dto.TaxIdentityDTO;
import com.ctytech.flierly.taxation.entity.TaxIdentity;
import com.ctytech.flierly.taxation.exception.TaxIdentityException;
import com.ctytech.flierly.taxation.mapper.TaxIdentityMapper;
import com.ctytech.flierly.taxation.repository.TaxIdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "/taxIdentityService")
public class TaxIdentityServiceImpl implements TaxIdentityService {

    @Autowired
    private TaxIdentityRepository taxIdentityRepository;

    @Autowired
    private TaxIdentityMapper taxIdentityMapper;

    @Override
    public TaxIdentityDTO save(TaxIdentityDTO taxIdentityDTO) throws TaxIdentityException {
        if (taxIdentityDTO.getIsActive() == null) taxIdentityDTO.setIsActive(true);

        if (taxIdentityDTO.getGst() != null && existsByGst(taxIdentityDTO.getGst()))
            throw new TaxIdentityException("TaxIdentityService.GST_ALREADY_EXISTS");

        if (taxIdentityDTO.getPan() != null && existsByPan(taxIdentityDTO.getPan()))
            throw new TaxIdentityException("TaxIdentityService.PAN_ALREADY_EXISTS");

        TaxIdentity taxIdentity = taxIdentityMapper.toEntity(taxIdentityDTO);

        return taxIdentityMapper.toDTO(taxIdentityRepository.save(taxIdentity));
    }

    @Override
    public TaxIdentityDTO fetch(Long id) throws TaxIdentityException {
        TaxIdentity taxIdentity = taxIdentityRepository.findById(id).orElseThrow(() -> new TaxIdentityException("TaxIdentityService.NOT_FOUND"));
        return taxIdentityMapper.toDTO(taxIdentity);
    }

    @Override
    public TaxIdentityDTO fetchByGstNumber(String gstNumber) throws TaxIdentityException {
        TaxIdentity taxIdentity = taxIdentityRepository.findByGst(gstNumber).orElseThrow(() -> new TaxIdentityException("TaxIdentityService.NOT_FOUND"));
        return taxIdentityMapper.toDTO(taxIdentity);
    }

    @Override
    public TaxIdentityDTO fetchByPanNumber(String panNumber) throws TaxIdentityException {
        TaxIdentity taxIdentity = taxIdentityRepository.findByPan(panNumber).orElseThrow(() -> new TaxIdentityException("TaxIdentityService.NOT_FOUND"));
        return taxIdentityMapper.toDTO(taxIdentity);
    }

    @Override
    public Boolean existsByGst(String gstNumber) {
        return taxIdentityRepository.existsByGst(gstNumber);
    }

    @Override
    public Boolean existsByPan(String panNumber) {
        return taxIdentityRepository.existsByPan(panNumber);
    }
}
