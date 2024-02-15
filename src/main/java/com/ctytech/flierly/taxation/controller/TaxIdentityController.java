package com.ctytech.flierly.taxation.controller;

import com.ctytech.flierly.taxation.dto.TaxIdentityDTO;
import com.ctytech.flierly.taxation.exception.TaxIdentityException;
import com.ctytech.flierly.taxation.service.TaxIdentityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/tax-identity")
public class TaxIdentityController {
    @Autowired
    private TaxIdentityService taxIdentityService;

    @PostMapping
    public ResponseEntity<TaxIdentityDTO> createTaxIdentity(@RequestBody @Valid TaxIdentityDTO newTaxIdentity) throws TaxIdentityException {
        TaxIdentityDTO taxIdentityDTO = taxIdentityService.save(newTaxIdentity);
        return new ResponseEntity<>(taxIdentityDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaxIdentityDTO> getById(@PathVariable(name = "id") Long id) throws TaxIdentityException {
        TaxIdentityDTO taxIdentityDTO = taxIdentityService.fetch(id);
        return new ResponseEntity<>(taxIdentityDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/gst/{gstNumber}")
    public ResponseEntity<TaxIdentityDTO> getByGst(@PathVariable(name = "gstNumber") String gstNumber) throws TaxIdentityException {
        TaxIdentityDTO taxIdentityDTO = taxIdentityService.fetchByGstNumber(gstNumber.toLowerCase());
        return new ResponseEntity<>(taxIdentityDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/pan/{panNumber}")
    public ResponseEntity<TaxIdentityDTO> getByPan(@PathVariable(name = "panNumber") String panNumber) throws TaxIdentityException {
        TaxIdentityDTO taxIdentityDTO = taxIdentityService.fetchByPanNumber(panNumber.toLowerCase());
        return new ResponseEntity<>(taxIdentityDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/gst-exists/{gstNumber}")
    public ResponseEntity<Boolean> checkExistenceWithGst(@PathVariable(name = "gstNumber") String gstNumber) {
        Boolean isExists = taxIdentityService.existsByGst(gstNumber);
        return new ResponseEntity<>(isExists, HttpStatus.OK);
    }

    @GetMapping(value = "/pan-exists/{panNumber}")
    public ResponseEntity<Boolean> checkExistenceWithPan(@PathVariable(name = "panNumber") String panNumber) {
        Boolean isExists = taxIdentityService.existsByPan(panNumber);
        return new ResponseEntity<>(isExists, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TaxIdentityDTO> updateById(@PathVariable(name = "id") Long id, @RequestBody @Valid TaxIdentityDTO update) throws TaxIdentityException {
        TaxIdentityDTO taxIdentityDTO = taxIdentityService.modify(id, update);
        return new ResponseEntity<>(taxIdentityDTO, HttpStatus.OK);
    }

}
