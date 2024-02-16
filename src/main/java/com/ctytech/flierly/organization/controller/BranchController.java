package com.ctytech.flierly.organization.controller;

import com.ctytech.flierly.organization.dto.BranchDTO;
import com.ctytech.flierly.organization.exception.BranchServiceException;
import com.ctytech.flierly.organization.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/branch")
@Validated
public class BranchController {
    @Autowired
    private BranchService branchService;

    @PostMapping
    public ResponseEntity<BranchDTO> createBranch(@RequestBody @Validated BranchDTO newBranch) throws BranchServiceException {
        BranchDTO branchDTO = branchService.save(newBranch);
        return new ResponseEntity<>(branchDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BranchDTO> getById(@PathVariable(name = "id") Long id) throws BranchServiceException {
        BranchDTO branchDTO = branchService.fetch(id);
        return new ResponseEntity<>(branchDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BranchDTO>> getAll() {
        List<BranchDTO> branchDTOS = branchService.fetchAll();
        return new ResponseEntity<>(branchDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/is-active/{id}")
    public ResponseEntity<Boolean> getIsActiveBranch(@PathVariable(name = "id") Long id) throws BranchServiceException {
        Boolean isActive = branchService.isActive(id);
        return new ResponseEntity<>(isActive, HttpStatus.OK);
    }

    @GetMapping(value = "/phone-exists/{phone}")
    public ResponseEntity<Boolean> checkExistenceWithPhone(@PathVariable(name = "phone") String phone) {
        Boolean isExists = branchService.existsByPhone(phone);
        return new ResponseEntity<>(isExists, HttpStatus.OK);
    }

    @GetMapping(value = "/email-exists/{email}")
    public ResponseEntity<Boolean> checkExistenceWithEmail(@PathVariable(name = "email") String email) {
        Boolean isExists = branchService.existsByEmail(email);
        return new ResponseEntity<>(isExists, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BranchDTO> updateById(@PathVariable(name = "id") Long id, @RequestBody @Validated BranchDTO update) throws BranchServiceException {
        BranchDTO branchDTO = branchService.modify(id, update);
        return new ResponseEntity<>(branchDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{branchId}/address/{addressId}")
    public ResponseEntity<BranchDTO> updateAddress(@PathVariable(name = "branchId") Long branchId, @PathVariable(name = "addressId") Long addressId) throws BranchServiceException {
        BranchDTO branchDTO = branchService.modifyAddressById(branchId, addressId);
        return new ResponseEntity<>(branchDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{branchId}/tax-identity/{taxIdentityId}")
    public ResponseEntity<BranchDTO> updateTaxIdentify(@PathVariable(name = "branchId") Long branchId, @PathVariable(name = "taxIdentityId") Long taxIdentityId) throws BranchServiceException {
        BranchDTO branchDTO = branchService.modifyTaxIdentityById(branchId, taxIdentityId);
        return new ResponseEntity<>(branchDTO, HttpStatus.OK);
    }
}
