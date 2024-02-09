package com.ctytech.flierly.address.controller;

import com.ctytech.flierly.address.dto.PostalIdentityDTO;
import com.ctytech.flierly.address.exception.PostalIdentityServiceException;
import com.ctytech.flierly.address.service.PostalIdentityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/postal-id")
public class PostalIdentityController {

    @Autowired
    private PostalIdentityService postalIdentityService;

    @PostMapping
    public ResponseEntity<PostalIdentityDTO> createPI(@RequestBody @Valid PostalIdentityDTO newPostalIdentityDTO) throws PostalIdentityServiceException {

        PostalIdentityDTO postalIdentityDTO = postalIdentityService.save(newPostalIdentityDTO);

        return new ResponseEntity<>(postalIdentityDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{pId}")
    public ResponseEntity<PostalIdentityDTO> getPIById(@PathVariable(name = "pId") Long pId) throws PostalIdentityServiceException {
        PostalIdentityDTO postalIdentityDTO = postalIdentityService.fetch(pId);

        return new ResponseEntity<>(postalIdentityDTO, HttpStatus.OK);
    }
    
}
