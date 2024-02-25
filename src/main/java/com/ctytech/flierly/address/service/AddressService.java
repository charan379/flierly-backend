package com.ctytech.flierly.address.service;

import com.ctytech.flierly.address.dto.AddressDTO;
import com.ctytech.flierly.address.exception.AddressServiceException;

import java.util.Set;

public interface AddressService {
    AddressDTO save(AddressDTO addressDTO) throws AddressServiceException;

    AddressDTO fetch(Long id) throws AddressServiceException;

    Set<AddressDTO> fetchAllByIds(Set<Long> ids);

    AddressDTO modify(Long id, AddressDTO update) throws AddressServiceException;
}
