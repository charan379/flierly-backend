package com.ctytech.flierly.organization.mapper;

import com.ctytech.flierly.organization.dto.OrganizationDTO;
import com.ctytech.flierly.organization.entity.Organization;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {

    @Autowired
    private ModelMapper modelMapper;

    public OrganizationDTO toDTO(Organization organization) {
        return modelMapper.map(organization, OrganizationDTO.class);
    }

    public Organization toEntity(OrganizationDTO organizationDTO) {
        return modelMapper.map(organizationDTO, Organization.class);
    }
}
