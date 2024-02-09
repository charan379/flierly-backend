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
        return modelMapper.map(postalIdentity, PostalIdentityDTO.class);
    }

    public PostalIdentity toEntity(PostalIdentityDTO postalIdentityDTO) {
        return modelMapper.map(postalIdentityDTO, PostalIdentity.class);
    }
}
