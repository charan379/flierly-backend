package com.ctytech.flierly.address.controller;

import com.ctytech.flierly.address.dto.PostalIdentityDTO;
import com.ctytech.flierly.address.exception.PostalIdentityServiceException;
import com.ctytech.flierly.address.service.PostalIdentityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/postal-id")
@Validated
public class PostalIdentityController {

    @Autowired
    private PostalIdentityService postalIdentityService;

    @PostMapping
    public ResponseEntity<PostalIdentityDTO> createPI(@RequestBody @Valid PostalIdentityDTO newPostalIdentityDTO) throws PostalIdentityServiceException {
        PostalIdentityDTO postalIdentityDTO = postalIdentityService.save(newPostalIdentityDTO);
        return new ResponseEntity<>(postalIdentityDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PostalIdentityDTO> getPIById(@PathVariable(name = "id") Long id) throws PostalIdentityServiceException {
        PostalIdentityDTO postalIdentityDTO = postalIdentityService.fetch(id);
        return new ResponseEntity<>(postalIdentityDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/pincode/{pincode}}")
    public ResponseEntity<PostalIdentityDTO> getByCountryIdAndPincode(@PathVariable(name = "pincode") Integer pincode, @RequestParam(name = "countryId") Long countryId) throws PostalIdentityServiceException {
        PostalIdentityDTO postalIdentityDTO = postalIdentityService.fetchByCountryIdAndPincode(countryId, pincode);
        return new ResponseEntity<>(postalIdentityDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PostalIdentityDTO>> getAllByCityId(@RequestParam(name = "cityId") Long cityId) {
        List<PostalIdentityDTO> listOfPIs = postalIdentityService.fetchAllByCityId(cityId);
        return new ResponseEntity<>(listOfPIs, HttpStatus.OK);
    }

    @GetMapping(value = "/pincode-exists/{pincode}")
    public ResponseEntity<Boolean> checkExistenceWithCountryIdAndPincode(@PathVariable(name = "pincode") Integer pincode, @RequestParam(name = "countryId") Long countryId) {
        Boolean isExists = postalIdentityService.existsByCountryIdAndPincode(countryId, pincode);
        return new ResponseEntity<>(isExists, HttpStatus.OK);
    }

    @GetMapping(value = "/valid-country-city")
    public ResponseEntity<Boolean> checkIfCityBelongsToCountry(@RequestParam(name = "countryId") Long countryId, @RequestParam(name = "cityId") Long cityId) {
        Boolean isTrue = postalIdentityService.isCityBelongsToCountry(countryId, cityId);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PostalIdentityDTO> updateById(@PathVariable(name = "id") Long id, @RequestBody @Valid PostalIdentityDTO update) throws PostalIdentityServiceException {
        PostalIdentityDTO postalIdentityDTO = postalIdentityService.modify(id, update);
        return new ResponseEntity<>(postalIdentityDTO, HttpStatus.OK);
    }
}
