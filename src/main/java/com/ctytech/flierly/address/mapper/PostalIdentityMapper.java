package com.ctytech.flierly.address.mapper;

import com.ctytech.flierly.address.dto.PostalIdentityDTO;
import com.ctytech.flierly.address.entity.PostalIdentity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostalIdentityMapper {

    @Autowired
    private ModelMapper modelMapper;

    public PostalIdentityDTO toDTO(PostalIdentity postalIdentity) {
        if (postalIdentity == null) return null;
        return modelMapper.map(postalIdentity, PostalIdentityDTO.class);
    }

    public PostalIdentityDTO toDTO(PostalIdentity postalIdentity, boolean isMinimal) {
        if (postalIdentity == null) return null;
        if (isMinimal) {
            PostalIdentityDTO dto = new PostalIdentityDTO();
            dto.setId(postalIdentity.getId());
            dto.setPinCode(postalIdentity.getPinCode());
            return dto;
        }
        return modelMapper.map(postalIdentity, PostalIdentityDTO.class);
    }

    public PostalIdentity toEntity(PostalIdentityDTO postalIdentityDTO) {
        return modelMapper.map(postalIdentityDTO, PostalIdentity.class);
    }
}
