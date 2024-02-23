package com.ctytech.flierly.utility;

import com.ctytech.flierly.address.dto.AddressDTO;
import com.ctytech.flierly.address.exception.AddressServiceException;
import com.ctytech.flierly.address.service.AddressService;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ModelMappingUtils {

    @Autowired
    private AddressService addressService;

    public Condition<String, String[]> canInclude(String fieldName, String... includes) {
        return (mappingContext) -> {
            // if includes is null or includes length = 0 return false
            if (includes == null || includes.length == 0) return false;
            // Check if fieldName is included in includes (case-insensitive)
            return Arrays.stream(includes).anyMatch(include -> include.equalsIgnoreCase(fieldName));
        };
    }

    public Converter<Long, AddressDTO> addressIdToAddressConverter = mappingContext -> {
        if (mappingContext.getSource() != null) {
            try {
                return addressService.fetch(mappingContext.getSource());
            } catch (AddressServiceException e) {
                return new AddressDTO();
            }
        }
        return null;
    };

}
