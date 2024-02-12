package com.ctytech.flierly.address.controller;

import com.ctytech.flierly.address.dto.StateDTO;
import com.ctytech.flierly.address.exception.StateServiceException;
import com.ctytech.flierly.address.service.StateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/state")
@Validated
public class StateController {

    @Autowired
    private StateService stateService;

    @PostMapping
    public ResponseEntity<StateDTO> createState(@RequestBody @Valid StateDTO newState) throws StateServiceException {
        StateDTO stateDTO = stateService.save(newState);
        return new ResponseEntity<>(stateDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StateDTO> getStateById(@PathVariable(name = "id") Long id) throws StateServiceException {
        StateDTO stateDTO = stateService.fetch(id);
        return new ResponseEntity<>(stateDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<StateDTO>> getAllByCountryId(@RequestParam(name = "countryId") Long countryId) throws StateServiceException {
        List<StateDTO> stateDTOS = stateService.fetchAllByCountryId(countryId);
        return new ResponseEntity<>(stateDTOS, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<StateDTO> updateById(@PathVariable(name = "id") Long id, @RequestBody @Valid StateDTO update) throws StateServiceException {
        StateDTO stateDTO = stateService.modify(id, update);
        return new ResponseEntity<>(stateDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/gst-code-exists/{gstCode}")
    public ResponseEntity<Boolean> checkExistenceWithGstCodeAndCountryId(@PathVariable(name = "gstCode") Integer gstCode, @RequestParam(name = "countryId") Long countryId) throws StateServiceException {
        Boolean exists = stateService.existsByGstCodeAndCountryId(gstCode, countryId);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    @GetMapping(value = "/code-exists/{code}")
    public ResponseEntity<Boolean> checkExistenceWithCodeAndCountryId(@PathVariable(name = "code") String code, @RequestParam(name = "countryId") Long countryId) throws StateServiceException {
        Boolean exists = stateService.exitsByCodeAndCountryId(code, countryId);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }
}
