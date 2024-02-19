package com.ctytech.flierly.contact.service;

import com.ctytech.flierly.contact.dto.ContactDTO;
import com.ctytech.flierly.contact.exception.ContactServiceException;

public interface ContactService {

    ContactDTO save(ContactDTO contactDTO) throws ContactServiceException;

    ContactDTO fetch(Long id) throws ContactServiceException;

    ContactDTO modify(Long id, ContactDTO update) throws ContactServiceException;

    ContactDTO modifyAddress(Long id, Long newAddressId) throws ContactServiceException;
}
