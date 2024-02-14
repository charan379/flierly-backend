package com.ctytech.flierly.address.controller;

import com.ctytech.flierly.address.dto.AddressDTO;
import com.ctytech.flierly.address.exception.AddressServiceException;
import com.ctytech.flierly.address.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/address")
@Validated
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressDTO> createAddress(@RequestBody @Valid AddressDTO newAddressDTO) throws AddressServiceException {
        AddressDTO addressDTO = addressService.save(newAddressDTO);
        return new ResponseEntity<>(addressDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable(name = "id") Long id) throws AddressServiceException {
        AddressDTO addressDTO = addressService.fetch(id);
        return new ResponseEntity<>(addressDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AddressDTO> updateById(@PathVariable(name = "id") Long id, @RequestBody @Valid AddressDTO update) throws AddressServiceException {
        AddressDTO addressDTO = addressService.modify(id, update);
        return new ResponseEntity<>(addressDTO, HttpStatus.OK);
    }
}
