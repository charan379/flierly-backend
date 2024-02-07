package com.ctytech.flierly.organization.controller;

import com.ctytech.flierly.organization.exception.OrganizationServiceException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/organization")
public class OrganizationController {


    @GetMapping(value = "/test")
    public void testApi() throws OrganizationServiceException {
        throw new OrganizationServiceException("Organization.INVALID");
    }

}
