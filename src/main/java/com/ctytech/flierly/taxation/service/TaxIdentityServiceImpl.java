package com.ctytech.flierly.taxation.service;

import com.ctytech.flierly.taxation.dto.TaxIdentityDTO;
import com.ctytech.flierly.taxation.entity.TaxIdentity;
import com.ctytech.flierly.taxation.exception.TaxIdentityException;
import com.ctytech.flierly.taxation.mapper.TaxIdentityMapper;
import com.ctytech.flierly.taxation.repository.TaxIdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service(value = "/taxIdentityService")
public class TaxIdentityServiceImpl implements TaxIdentityService {

    @Autowired
    private TaxIdentityRepository taxIdentityRepository;

    @Autowired
    private TaxIdentityMapper taxIdentityMapper;

    @Override
    public TaxIdentityDTO save(TaxIdentityDTO taxIdentityDTO) throws TaxIdentityException {
        // Set isActive to true if not provided
        if (taxIdentityDTO.getIsActive() == null) taxIdentityDTO.setIsActive(true);
        // Check if provided gst already exists
        if (taxIdentityDTO.getGst() != null && existsByGst(taxIdentityDTO.getGst()))
            throw new TaxIdentityException("TaxIdentityService.GST_ALREADY_EXISTS");
        // Check if provided pan already exists
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
    public TaxIdentityDTO modify(Long id, TaxIdentityDTO update) throws TaxIdentityException {
        TaxIdentity taxIdentity = taxIdentityRepository.findById(id).orElseThrow(() -> new TaxIdentityException("TaxIdentityService.NOT_FOUND"));
        // Set isActive if provided, otherwise keep the current value
        taxIdentity.setIsActive(Objects.requireNonNullElse(update.getIsActive(), taxIdentity.getIsActive()));
        // Set GST if not present and provided
        if (taxIdentity.getGst() == null && update.getGst() != null) {
            // Check if provided gst already exists
            if (existsByGst(update.getGst())) throw new TaxIdentityException("TaxIdentityService.GST_ALREADY_EXISTS");
            // If not exits then do update
            taxIdentity.setGst(update.getGst());
        }
        // Update GST related fields
        taxIdentity.setGstVerified(Objects.requireNonNullElse(update.getGstVerified(), taxIdentity.getGstVerified()));
        taxIdentity.setGstRegistrationDate(Objects.requireNonNullElse(update.getGstRegistrationDate(), taxIdentity.getGstRegistrationDate()));
        taxIdentity.setGstRegistrationAddressId(Objects.requireNonNullElse(update.getGstRegistrationAddressId(), taxIdentity.getGstRegistrationAddressId()));
        // Set PAN if not present and provided
        if (taxIdentity.getPan() == null && update.getPan() != null) {
            // Check if provided pan already exists
            if (existsByPan(update.getPan())) throw new TaxIdentityException("TaxIdentityService.PAN_ALREADY_EXISTS");
            // if not exists then do update
            taxIdentity.setPan(update.getPan());
        }
        // Update PAN verification status
        taxIdentity.setPanVerified(Objects.requireNonNullElse(update.getPanVerified(), taxIdentity.getPanVerified()));
        // Save and return DTO
        return taxIdentityMapper.toDTO(taxIdentityRepository.saveAndFlush(taxIdentity));
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
