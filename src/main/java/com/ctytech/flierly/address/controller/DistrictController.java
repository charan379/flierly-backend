package com.ctytech.flierly.address.controller;

import com.ctytech.flierly.address.dto.DistrictDTO;
import com.ctytech.flierly.address.exception.DistrictServiceException;
import com.ctytech.flierly.address.service.DistrictService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/district")
@Validated
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @PostMapping
    public ResponseEntity<DistrictDTO> createDistrict(@RequestBody @Valid DistrictDTO newDistrictDTO) throws DistrictServiceException {
        DistrictDTO districtDTO = districtService.save(newDistrictDTO);
        return new ResponseEntity<>(districtDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DistrictDTO> getById(@PathVariable("id") Long id) throws DistrictServiceException {
        DistrictDTO districtDTO = districtService.fetch(id);
        return new ResponseEntity<>(districtDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/code/{code}")
    public ResponseEntity<DistrictDTO> getByCodeAndStateId(@PathVariable("code") String code, @RequestParam("stateId") Long stateId) throws DistrictServiceException {
        DistrictDTO districtDTO = districtService.fetch(code.toLowerCase(), stateId);
        return new ResponseEntity<>(districtDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DistrictDTO>> getAllByStateId(@RequestParam("stateId") Long stateId) throws DistrictServiceException {
        List<DistrictDTO> districtDTOS = districtService.fetchAllByStateId(stateId);
        return new ResponseEntity<>(districtDTOS, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DistrictDTO> updateById(@PathVariable("id") Long id, @RequestBody @Valid DistrictDTO update) throws DistrictServiceException {
        DistrictDTO districtDTO = districtService.modify(id, update);
        return new ResponseEntity<>(districtDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/code-exists/{code}")
    public ResponseEntity<Boolean> checkExistenceWithCodeAndStateId(@PathVariable("code") String code, @RequestParam("stateId") Long stateId) throws DistrictServiceException {
        Boolean isExists = districtService.existsByCodeAndStateId(code.toLowerCase(), stateId);
        return new ResponseEntity<>(isExists, HttpStatus.OK);
    }

    @GetMapping(value = "/landline-code-exists/{landlineCode}")
    public ResponseEntity<Boolean> checkExistenceWithLandlineCodeAndStateId(@PathVariable("landlineCode") Integer landlineCode, @RequestParam("stateId") Long stateId) throws DistrictServiceException {
        Boolean isExists = districtService.existsByLandlineCodeAndStateId(landlineCode, stateId);
        return new ResponseEntity<>(isExists, HttpStatus.OK);
    }
}
