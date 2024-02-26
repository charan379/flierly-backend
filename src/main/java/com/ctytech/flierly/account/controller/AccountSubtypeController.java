package com.ctytech.flierly.account.controller;

import com.ctytech.flierly.account.dto.AccountSubtypeDTO;
import com.ctytech.flierly.account.exception.AccountServiceException;
import com.ctytech.flierly.account.service.AccountSubtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/account-subtype")
@Validated
public class AccountSubtypeController {
    @Autowired
    private AccountSubtypeService accountSubtypeService;

    @PostMapping
    public ResponseEntity<AccountSubtypeDTO> createSubtype(@RequestBody @Validated AccountSubtypeDTO newAccountSubtype) throws AccountServiceException {
        AccountSubtypeDTO subtype = accountSubtypeService.save(newAccountSubtype);
        return new ResponseEntity<>(subtype, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountSubtypeDTO> getById(@PathVariable(name = "id") Long id) throws AccountServiceException {
        AccountSubtypeDTO subtype = accountSubtypeService.fetch(id);
        return new ResponseEntity<>(subtype, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AccountSubtypeDTO>> getAll() {
        List<AccountSubtypeDTO> subtypes = accountSubtypeService.fetchAll();
        return new ResponseEntity<>(subtypes, HttpStatus.OK);
    }

    @GetMapping(value = "/name-exists/{name}")
    public ResponseEntity<Boolean> checkExistenceWithName(@PathVariable(name = "name") String name) {
        Boolean isExists = accountSubtypeService.existsByName(name);
        return new ResponseEntity<>(isExists, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AccountSubtypeDTO> updateById(@PathVariable(name = "id") Long id, @RequestBody @Validated AccountSubtypeDTO update) throws AccountServiceException {
        AccountSubtypeDTO subtype = accountSubtypeService.modify(id, update);
        return new ResponseEntity<>(subtype, HttpStatus.OK);
    }

}
