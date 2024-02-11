package com.ctytech.flierly.address.controller;

import com.ctytech.flierly.address.dto.CountryDTO;
import com.ctytech.flierly.address.exception.CountryServiceException;
import com.ctytech.flierly.address.service.CountryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping
    public ResponseEntity<CountryDTO> createCountry(@RequestBody @Valid CountryDTO newCountry) throws CountryServiceException {
        CountryDTO countryDTO = countryService.save(newCountry);
        return new ResponseEntity<>(countryDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CountryDTO> getCountryById(@PathVariable(name = "id") Long id) throws CountryServiceException {
        CountryDTO countryDTO = countryService.fetch(id);
        return new ResponseEntity<>(countryDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/code/{code}")
    public ResponseEntity<CountryDTO> getCountryByCode(@PathVariable(name = "code") String code) throws CountryServiceException {
        CountryDTO countryDTO = countryService.fetch(code.toLowerCase());
        return new ResponseEntity<>(countryDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CountryDTO>> getAllCountries() throws CountryServiceException {
        List<CountryDTO> countries = countryService.fetchAll();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CountryDTO> updateById(@PathVariable(name = "id") Long id, @RequestBody @Valid CountryDTO update) throws CountryServiceException {
        CountryDTO updatedCountry = countryService.modify(id, update);
        return new ResponseEntity<>(updatedCountry, HttpStatus.OK);
    }

    @GetMapping(value = "/code-exists/{code}")
    public ResponseEntity<Boolean> countryCodeExists(@PathVariable(name = "code") String code) throws CountryServiceException {
        Boolean isExists = countryService.existsByCode(code.toLowerCase());
        return new ResponseEntity<>(isExists, HttpStatus.OK);
    }

    @GetMapping(value = "/dialing-code-exists/{dialingCode}")
    public ResponseEntity<Boolean> countryDialingCodeExists(@PathVariable(name = "dialingCode") Integer dialingCode) throws CountryServiceException {
        Boolean isExists = countryService.existsByDialingCode(dialingCode);
        return new ResponseEntity<>(isExists, HttpStatus.OK);
    }
}
