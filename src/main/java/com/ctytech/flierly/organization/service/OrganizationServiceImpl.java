package com.ctytech.flierly.organization.service;

import com.ctytech.flierly.organization.dto.OrganizationDTO;
import com.ctytech.flierly.organization.entity.Organization;
import com.ctytech.flierly.organization.exception.OrganizationServiceException;
import com.ctytech.flierly.organization.mapper.OrganizationMapper;
import com.ctytech.flierly.organization.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "organizationService")
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public OrganizationDTO save(OrganizationDTO organizationDTO) throws OrganizationServiceException {

        List<Organization> organizations = organizationRepository.findAll();

        if (!organizations.isEmpty()) throw new OrganizationServiceException("Organization.PRESENT");

        Organization newOrganization = organizationRepository.save(organizationMapper.toEntity(organizationDTO));

        return organizationMapper.toDTO(newOrganization);
    }

    @Override
    public OrganizationDTO fetch(Long id) throws OrganizationServiceException {

        Organization organization = organizationRepository.findById(id).orElseThrow(() -> new OrganizationServiceException("Organization.NOT_FOUND"));

        return organizationMapper.toDTO(organization);
    }

    @Override
    public OrganizationDTO modify(Long id, OrganizationDTO organizationDTO) throws OrganizationServiceException {

        Organization organization = organizationRepository.findById(id).orElseThrow(() -> new OrganizationServiceException("Organization.NOT_FOUND"));

        organization.setName(organizationDTO.getName());
        organization.setEmail(organizationDTO.getEmail());
        organization.setPhone(organizationDTO.getPhone());

        Organization modifiedOrganization = organizationRepository.save(organization);

        return organizationMapper.toDTO(modifiedOrganization);
    }
}
