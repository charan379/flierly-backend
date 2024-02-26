package com.ctytech.flierly.organization.controller;

import com.ctytech.flierly.organization.dto.OrganizationDTO;
import com.ctytech.flierly.organization.exception.OrganizationServiceException;
import com.ctytech.flierly.organization.service.OrganizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/organization")
@Validated
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationDTO> createOrganization(@RequestBody @Valid OrganizationDTO newOrganization) throws OrganizationServiceException {

        OrganizationDTO organizationDTO = organizationService.save(newOrganization);

        return new ResponseEntity<>(organizationDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrganizationDTO> getOrganization(@PathVariable(name = "id") Long id) throws OrganizationServiceException {

        OrganizationDTO organizationDTO = organizationService.fetch(id);

        return new ResponseEntity<>(organizationDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OrganizationDTO> updateOrganization(@PathVariable(name = "id") Long id, @RequestBody @Valid OrganizationDTO update) throws OrganizationServiceException {

        OrganizationDTO organizationDTO = organizationService.modify(id, update);

        return new ResponseEntity<>(organizationDTO, HttpStatus.OK);
    }

}
