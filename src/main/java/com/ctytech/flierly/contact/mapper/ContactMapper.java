package com.ctytech.flierly.contact.mapper;

import com.ctytech.flierly.FlierlyException;
import com.ctytech.flierly.address.dto.AddressDTO;
import com.ctytech.flierly.address.service.AddressService;
import com.ctytech.flierly.contact.dto.ContactDTO;
import com.ctytech.flierly.contact.enitity.Contact;
import com.ctytech.flierly.utility.ModelMappingUtils;
import jakarta.annotation.PostConstruct;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ModelMappingUtils modelMappingUtils;

    @PostConstruct
    public void init() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setImplicitMappingEnabled(false);

        // ContactDTO -> Contact entity mapping
        modelMapper.createTypeMap(ContactDTO.class, Contact.class)
                .addMappings(mapper -> mapper.when(Conditions.isNotNull()).map(ContactDTO::getId, Contact::setId))
                .addMapping(ContactDTO::getName, Contact::setName)
                .addMapping(ContactDTO::getPhone, Contact::setPhone)
                .addMapping(ContactDTO::getAlternatePhone, Contact::setAlternatePhone)
                .addMapping(ContactDTO::getEmail, Contact::setEmail)
                .addMapping(ContactDTO::getAddressId, Contact::setAddressId);

        // Contact -> ContactDTO mapping
        modelMapper.createTypeMap(Contact.class, ContactDTO.class)
                .addMapping(Contact::getId, ContactDTO::setId)
                .addMapping(Contact::getName, ContactDTO::setName)
                .addMapping(Contact::getPhone, ContactDTO::setPhone)
                .addMapping(Contact::getAlternatePhone, ContactDTO::setAlternatePhone)
                .addMapping(Contact::getEmail, ContactDTO::setEmail)
                .addMapping(Contact::getAddressId, ContactDTO::setAddressId);
    }

    private final Converter<Long, AddressDTO> addressIdToAddressConverter = mappingContext -> {
        if (mappingContext.getSource() != null) {
            try {
                return addressService.fetch(mappingContext.getSource());
            } catch (FlierlyException e) {
                return new AddressDTO();
            }
        }
        return null;
    };

    public ContactDTO toDTO(Contact contact, String... includeDTOs) {
        if (contact == null) return null;
        // Include AddressDTO based on includes
        modelMapper.getTypeMap(Contact.class, ContactDTO.class)
                .addMappings(mapper -> mapper
                        .when(modelMappingUtils.canInclude("address", includeDTOs))
                        .using(addressIdToAddressConverter)
                        .map(Contact::getAddressId, ContactDTO::setAddress)
                );
        // return contactDTO
        return modelMapper.map(contact, ContactDTO.class);
    }

    public Contact toEntity(ContactDTO contactDTO) {
        return modelMapper.map(contactDTO, Contact.class);
    }
}
