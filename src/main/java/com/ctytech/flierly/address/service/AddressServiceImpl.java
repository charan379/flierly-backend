package com.ctytech.flierly.address.service;

import com.ctytech.flierly.address.dto.*;
import com.ctytech.flierly.address.entity.Address;
import com.ctytech.flierly.address.exception.AddressServiceException;
import com.ctytech.flierly.address.exception.AreaServiceException;
import com.ctytech.flierly.address.mapper.AddressMapper;
import com.ctytech.flierly.address.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service(value = "addressService")
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public AddressDTO save(AddressDTO addressDTO) throws AddressServiceException {

        Long countryId = Optional.ofNullable(addressDTO.getCountry()).map(CountryDTO::getId).orElseThrow(() -> new AddressServiceException("AddressService.COUNTRY_ID_ABSENT"));
        Long stateId = Optional.ofNullable(addressDTO.getState()).map(StateDTO::getId).orElseThrow(() -> new AddressServiceException("AddressService.STATE_ID_ABSENT"));
        Long districtId = Optional.ofNullable(addressDTO.getDistrict()).map(DistrictDTO::getId).orElseThrow(() -> new AddressServiceException("AddressService.DISTRICT_ID_ABSENT"));
        Long cityId = Optional.ofNullable(addressDTO.getCity()).map(CityDTO::getId).orElseThrow(() -> new AddressServiceException("AddressService.CITY_ID_ABSENT"));
        Long pId = Optional.ofNullable(addressDTO.getPostalIdentity()).map(PostalIdentityDTO::getId).orElseThrow(() -> new AddressServiceException("AddressService.POSTAL_ID_ABSENT"));
        Long areaId = Optional.ofNullable(addressDTO.getArea()).map(AreaDTO::getId).orElseThrow(() -> new AddressServiceException("AddressService.AREA_ID_ABSENT"));

        if (!addressRepository.validAreaMapping(areaId, pId, cityId, districtId, stateId, countryId))
            throw new AreaServiceException("AddressService.INVALID_AREA_MAPPING");

        addressDTO.setIsActive(Optional.ofNullable(addressDTO.getIsActive()).orElse(false));
        addressDTO.setIsPrimary(false);

        Address address = addressMapper.toEntity(addressDTO);

        return addressMapper.toDTO(addressRepository.save(address));
    }

    @Override
    public AddressDTO fetch(Long id) throws AddressServiceException {
        Address address = addressRepository.findById(id).orElseThrow(() -> new AddressServiceException("AddressService.NOT_FOUND"));
        return addressMapper.toDTO(address);
    }

    @Override
    public Set<AddressDTO> fetchAllByIds(Set<Long> ids) {
        return addressRepository.findAllById(ids).stream()
                .map(address -> addressMapper.toDTO(address))
                .collect(Collectors.toSet());
    }

    @Override
    public AddressDTO modify(Long id, AddressDTO update) throws AddressServiceException {
        Address address = addressRepository.findById(id).orElseThrow(() -> new AddressServiceException("AddressService.NOT_FOUND"));

        if (update.getIsActive() != null) address.setIsActive(update.getIsActive());
        address.setLine1(update.getLine1());
        address.setLine2(update.getLine2());
        address.setLine3(update.getLine3());
        address.setLandMark(update.getLandMark());
        address.setLatitude(update.getLatitude());
        address.setLongitude(update.getLongitude());

        return addressMapper.toDTO(addressRepository.save(address));
    }
}
