package com.ctytech.flierly.account.controller;

import com.ctytech.flierly.account.dto.AccountTypeDTO;
import com.ctytech.flierly.account.exception.AccountServiceException;
import com.ctytech.flierly.account.service.AccountTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/account-types")
@Validated
public class AccountTypeController {
    @Autowired
    private AccountTypeService accountTypeService;

    @PostMapping
    public ResponseEntity<AccountTypeDTO> createAccountType(@RequestBody @Validated AccountTypeDTO newAccountType) throws AccountServiceException {
        AccountTypeDTO accountTypeDTO = accountTypeService.save(newAccountType);
        return new ResponseEntity<>(accountTypeDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountTypeDTO> getById(@PathVariable(name = "id") Long id,
                                                  @RequestParam(name = "append_to_response", required = false) String... includesDTOs
    ) throws AccountServiceException {
        AccountTypeDTO accountTypeDTO = accountTypeService.fetch(id, includesDTOs);
        return new ResponseEntity<>(accountTypeDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AccountTypeDTO>> getAll(@RequestParam(name = "append_to_response", required = false) String... includesDTOs) {
        List<AccountTypeDTO> accountTypeDTOS = accountTypeService.fetchAll(includesDTOs);
        return new ResponseEntity<>(accountTypeDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/name-exists/{name}")
    public ResponseEntity<Boolean> checkExistenceWithName(@PathVariable(name = "name") String name) {
        Boolean isExists = accountTypeService.existsByName(name);
        return new ResponseEntity<>(isExists, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AccountTypeDTO> updateById(@PathVariable(name = "id") Long id, @RequestBody @Valid AccountTypeDTO update) throws AccountServiceException {
        AccountTypeDTO accountTypeDTO = accountTypeService.modify(id, update);
        return new ResponseEntity<>(accountTypeDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/subtypes")
    public ResponseEntity<AccountTypeDTO> updateSubtypesById(@PathVariable(name = "id") Long id, @RequestBody @Valid Set<Long> subtypesIds) throws AccountServiceException {
        AccountTypeDTO accountTypeDTO = accountTypeService.modifySubtypes(id, subtypesIds);
        return new ResponseEntity<>(accountTypeDTO, HttpStatus.OK);
    }
}
