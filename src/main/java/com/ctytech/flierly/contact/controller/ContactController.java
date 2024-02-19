package com.ctytech.flierly.contact.controller;

import com.ctytech.flierly.contact.dto.ContactDTO;
import com.ctytech.flierly.contact.exception.ContactServiceException;
import com.ctytech.flierly.contact.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/contacts")
@Validated
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<ContactDTO> create(@RequestBody @Valid ContactDTO newContact) throws ContactServiceException {
        ContactDTO contactDTO = contactService.save(newContact);
        return new ResponseEntity<>(contactDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ContactDTO> getById(@PathVariable(name = "id") Long id) throws ContactServiceException {
        ContactDTO contactDTO = contactService.fetch(id);
        return new ResponseEntity<>(contactDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ContactDTO> updateById(@PathVariable(name = "id") Long id, @RequestBody @Valid ContactDTO update) throws ContactServiceException {
        ContactDTO contactDTO = contactService.modify(id, update);
        return new ResponseEntity<>(contactDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/{contactId}/address/{addressId}")
    public ResponseEntity<ContactDTO> updateAddressById(Long contactId, Long addressId) throws ContactServiceException {
        ContactDTO contactDTO = contactService.modifyAddress(contactId, addressId);
        return new ResponseEntity<>(contactDTO, HttpStatus.OK);
    }
}
