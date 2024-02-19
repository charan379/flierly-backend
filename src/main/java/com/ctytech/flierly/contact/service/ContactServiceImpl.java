package com.ctytech.flierly.contact.service;

import com.ctytech.flierly.address.dto.AddressDTO;
import com.ctytech.flierly.address.exception.AddressServiceException;
import com.ctytech.flierly.address.service.AddressService;
import com.ctytech.flierly.contact.dto.ContactDTO;
import com.ctytech.flierly.contact.enitity.Contact;
import com.ctytech.flierly.contact.exception.ContactServiceException;
import com.ctytech.flierly.contact.mapper.ContactMapper;
import com.ctytech.flierly.contact.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "contactService")
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ContactMapper contactMapper;

    @Override
    public ContactDTO save(ContactDTO contactDTO) throws ContactServiceException {
        // Convert contactDTO to entity
        Contact contact = contactMapper.toEntity(contactDTO);
        // Check if addressId provided, then get id
        Long addressId = Optional.ofNullable(contactDTO.getAddress()).map(AddressDTO::getId).orElse(null);
        // If addressId provided, validate it
        if (addressId != null) {
            // fetch address by id, and set tp new contact entity
            try {
                AddressDTO addressDTO = addressService.fetch(addressId);
                contact.setAddress(contactMapper.getAddressMapper().toEntity(addressDTO));
            } catch (AddressServiceException e) {
                // if invalid addressId throw error
                throw new ContactServiceException(e.getMessage());
            }
        }
        // Save contact and return it as DTO
        return contactMapper.toDTO(contactRepository.save(contact));
    }

    @Override
    public ContactDTO fetch(Long id) throws ContactServiceException {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new ContactServiceException("Contact.NOT_FOUND"));
        return contactMapper.toDTO(contact);
    }

    @Override
    public ContactDTO modify(Long id, ContactDTO update) throws ContactServiceException {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new ContactServiceException("Contact.NOT_FOUND"));
        contact.setName(update.getName());
        contact.setPhone(update.getPhone());
        contact.setAlternatePhone(update.getAlternatePhone());
        contact.setEmail(update.getEmail());
        return contactMapper.toDTO(contactRepository.save(contact));
    }

    @Override
    public ContactDTO modifyAddress(Long id, Long newAddressId) throws ContactServiceException {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new ContactServiceException("Contact.NOT_FOUND"));
        // Validate new address id
        // If valid address then do update
        try {
            AddressDTO addressDTO = addressService.fetch(newAddressId);
            contact.setAddress(contactMapper.getAddressMapper().toEntity(addressDTO));
        } catch (AddressServiceException e) {
            // if invalid addressId throw error
            throw new ContactServiceException(e.getMessage());
        }
        return contactMapper.toDTO(contactRepository.save(contact));
    }
}
