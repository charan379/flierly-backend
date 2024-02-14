package com.ctytech.flierly.address.service;

import com.ctytech.flierly.address.dto.AddressDTO;
import com.ctytech.flierly.address.exception.AddressServiceException;

public interface AddressService {
    AddressDTO save(AddressDTO addressDTO) throws AddressServiceException;

    AddressDTO fetch(Long id) throws AddressServiceException;

    AddressDTO modify(Long id, AddressDTO update) throws AddressServiceException;
}
