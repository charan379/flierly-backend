package com.ctytech.flierly.contact.mapper;

import com.ctytech.flierly.address.mapper.AddressMapper;
import com.ctytech.flierly.contact.dto.ContactDTO;
import com.ctytech.flierly.contact.enitity.Contact;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Getter
    @Autowired
    private AddressMapper addressMapper;

    public ContactDTO toDTO(Contact contact) {
        if (contact == null) return null;
        return modelMapper.map(contact, ContactDTO.class);
    }

    public Contact toEntity(ContactDTO contactDTO) {
        return modelMapper.map(contactDTO, Contact.class);
    }
}
