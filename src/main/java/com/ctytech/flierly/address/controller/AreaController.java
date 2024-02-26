package com.ctytech.flierly.address.controller;

import com.ctytech.flierly.address.dto.AreaDTO;
import com.ctytech.flierly.address.exception.AreaServiceException;
import com.ctytech.flierly.address.service.AreaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/area")
@Validated
public class AreaController {

    @Autowired
    private AreaService areaService;

    @PostMapping
    public ResponseEntity<AreaDTO> createArea(@RequestBody @Valid AreaDTO newArea) throws AreaServiceException {
        AreaDTO areaDTO = areaService.save(newArea);
        return new ResponseEntity<>(areaDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AreaDTO> getAreaById(@PathVariable(name = "id") Long id) throws AreaServiceException {
        AreaDTO areaDTO = areaService.fetch(id);
        return new ResponseEntity<>(areaDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AreaDTO>> getAreasByPid(@RequestParam(name = "postalId", defaultValue = "0") Long postalId) throws AreaServiceException {
        List<AreaDTO> areaDTOS = areaService.fetchAllByPI(postalId);
        return new ResponseEntity<>(areaDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/exists")
    public ResponseEntity<Boolean> checkExistenceWithNameAndPid(@RequestParam(name = "postalId") Long postalId, @RequestParam(name = "name") String name) {
        Boolean isExists = areaService.existsByPostalIdAndName(postalId, name);
        return new ResponseEntity<>(isExists, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AreaDTO> updateAreaById(@PathVariable(name = "id") Long id, @RequestBody @Valid AreaDTO update) throws AreaServiceException {
        AreaDTO areaDTO = areaService.modify(id, update);
        return new ResponseEntity<>(areaDTO, HttpStatus.OK);
    }


}
