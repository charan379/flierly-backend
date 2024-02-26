package com.ctytech.flierly.contact.service;

import com.ctytech.flierly.contact.dto.ContactDTO;
import com.ctytech.flierly.contact.enitity.Contact;
import com.ctytech.flierly.contact.exception.ContactServiceException;
import com.ctytech.flierly.contact.mapper.ContactMapper;
import com.ctytech.flierly.contact.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service(value = "contactService")
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactMapper contactMapper;

    @Override
    public ContactDTO save(ContactDTO contactDTO) throws ContactServiceException {
        // Convert contactDTO to entity
        Contact contact = contactMapper.toEntity(contactDTO);
        // Save contact and return it as DTO
        return contactMapper.toDTO(contactRepository.save(contact));
    }

    @Override
    public ContactDTO fetch(Long id, String... includesDTOs) throws ContactServiceException {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new ContactServiceException("Contact.NOT_FOUND"));
        return contactMapper.toDTO(contact, includesDTOs);
    }

    @Override
    public Set<ContactDTO> fetchAllByIds(Set<Long> ids, String... includesDTOs) {
        return contactRepository.findAllById(ids).stream()
                .map(contact -> contactMapper.toDTO(contact, includesDTOs))
                .collect(Collectors.toSet());
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
    public ContactDTO modifyAddress(Long id, Long addressId) throws ContactServiceException {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new ContactServiceException("Contact.NOT_FOUND"));
        // Extract existing addressId from contact if exists
        Long existingAddressId = contact.getAddressId();
        // Update Address if existing addressId and new addressId are different
        if (!Objects.equals(existingAddressId, addressId)) {
            contact.setAddressId(addressId);
        }
        return contactMapper.toDTO(contactRepository.save(contact));
    }
}
