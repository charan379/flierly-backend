package com.ctytech.flierly.taxation.service;

import com.ctytech.flierly.address.dto.AddressDTO;
import com.ctytech.flierly.address.entity.Address;
import com.ctytech.flierly.address.exception.AddressServiceException;
import com.ctytech.flierly.address.mapper.AddressMapper;
import com.ctytech.flierly.address.service.AddressService;
import com.ctytech.flierly.taxation.dto.TaxIdentityDTO;
import com.ctytech.flierly.taxation.entity.TaxIdentity;
import com.ctytech.flierly.taxation.exception.TaxIdentityException;
import com.ctytech.flierly.taxation.mapper.TaxIdentityMapper;
import com.ctytech.flierly.taxation.repository.TaxIdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service(value = "/taxIdentityService")
public class TaxIdentityServiceImpl implements TaxIdentityService {

    @Autowired
    private TaxIdentityRepository taxIdentityRepository;

    @Autowired
    private TaxIdentityMapper taxIdentityMapper;

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressMapper addressMapper;

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
    public TaxIdentityDTO modify(Long id, TaxIdentityDTO update) throws TaxIdentityException {
        TaxIdentity taxIdentity = taxIdentityRepository.findById(id).orElseThrow(() -> new TaxIdentityException("TaxIdentityService.NOT_FOUND"));
        // Set isActive if provided, otherwise keep the current value
        taxIdentity.setIsActive(Objects.requireNonNullElse(update.getIsActive(), taxIdentity.getIsActive()));
        // Set GST if not present and provided
        if (taxIdentity.getGst() == null && update.getGst() != null) taxIdentity.setGst(update.getGst());
        // Update GST related fields
        taxIdentity.setGstVerified(Objects.requireNonNullElse(update.getGstVerified(), taxIdentity.getGstVerified()));
        taxIdentity.setGstRegistrationDate(Objects.requireNonNullElse(update.getGstRegistrationDate(), taxIdentity.getGstRegistrationDate()));
        // Update GST registration address if changed
        Long currentGstAddressId = Optional.ofNullable(taxIdentity.getGstRegistrationAddress()).map(Address::getId).orElse(null);
        Long newGstAddressId = Optional.ofNullable(update.getGstRegistrationAddress()).map(AddressDTO::getId).orElse(null);
        if (!Objects.equals(newGstAddressId, currentGstAddressId)) if (newGstAddressId != null) {
            try {
                taxIdentity.setGstRegistrationAddress(addressMapper.toEntity(addressService.fetch(newGstAddressId)));
            } catch (AddressServiceException e) {
                throw new TaxIdentityException(e.getMessage());
            }
        }
        // Set PAN if not present and provided
        if (taxIdentity.getPan() == null && update.getPan() != null) taxIdentity.setPan(update.getPan());
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
