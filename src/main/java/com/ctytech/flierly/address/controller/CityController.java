package com.ctytech.flierly.address.controller;

import com.ctytech.flierly.address.dto.CityDTO;
import com.ctytech.flierly.address.exception.CityServiceException;
import com.ctytech.flierly.address.service.CityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/city")
@Validated
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping
    public ResponseEntity<CityDTO> createCity(@RequestBody @Valid CityDTO newCity) throws CityServiceException {
        CityDTO city = cityService.save(newCity);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CityDTO> getById(@PathVariable(name = "id") Long id) throws CityServiceException {
        CityDTO city = cityService.fetch(id);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @GetMapping(value = "/code/{code}")
    public ResponseEntity<CityDTO> getByCodeAndDistrictId(@PathVariable(name = "code") String code, @RequestParam(name = "districtId") Long districtId) throws CityServiceException {
        CityDTO city = cityService.fetch(code.toLowerCase(), districtId);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CityDTO>> getAllByDistrictId(@RequestParam("districtId") Long districtId) {
        List<CityDTO> cityDTOS = cityService.fetchAllByDistrictId(districtId);
        return new ResponseEntity<>(cityDTOS, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CityDTO> updateById(@PathVariable(name = "id") Long id, @RequestBody @Valid CityDTO update) throws CityServiceException {
        CityDTO cityDTO = cityService.modify(id, update);
        return new ResponseEntity<>(cityDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/code-exists/{code}")
    public ResponseEntity<Boolean> checkExistenceWithCodeAndDistrictId(@PathVariable(name = "code") String code, @RequestParam(name = "districtId") Long districtId) {
        Boolean isExists = cityService.existsByCodeAndDistrictId(code.toLowerCase(), districtId);
        return new ResponseEntity<>(isExists, HttpStatus.OK);
    }


}
