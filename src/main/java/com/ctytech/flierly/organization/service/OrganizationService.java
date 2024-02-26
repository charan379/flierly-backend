package com.ctytech.flierly.organization.service;

import com.ctytech.flierly.organization.dto.OrganizationDTO;
import com.ctytech.flierly.organization.exception.OrganizationServiceException;

public interface OrganizationService {

    OrganizationDTO save(OrganizationDTO organizationDTO) throws OrganizationServiceException;

    OrganizationDTO fetch(Long id) throws OrganizationServiceException;

    OrganizationDTO modify(Long id, OrganizationDTO organizationDTO) throws OrganizationServiceException;
}
