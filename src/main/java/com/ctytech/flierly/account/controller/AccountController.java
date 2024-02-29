package com.ctytech.flierly.account.controller;

import com.ctytech.flierly.account.dto.AccountDTO;
import com.ctytech.flierly.account.dto.UpdateAccountDTO;
import com.ctytech.flierly.account.exception.AccountServiceException;
import com.ctytech.flierly.account.service.AccountService;
import com.ctytech.flierly.address.dto.AddressDTO;
import com.ctytech.flierly.contact.dto.ContactDTO;
import com.ctytech.flierly.organization.dto.BranchDTO;
import com.ctytech.flierly.taxation.dto.TaxIdentityDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/account")
@Validated
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody @Validated AccountDTO newAccount) throws AccountServiceException {
        AccountDTO accountDTO = accountService.save(newAccount);
        return new ResponseEntity<>(accountDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable(name = "id") Long id) throws AccountServiceException {
        AccountDTO accountDTO = accountService.fetch(id);
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);

    }

    @GetMapping(value = "/allAccounts")
    public ResponseEntity<Set<AccountDTO>> getAllAccounts(@RequestParam Set<Long> ids) throws AccountServiceException {
        Set<AccountDTO> accountDTO = accountService.fetchAllByIds(ids);
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AccountDTO> updateByID(@PathVariable(name = "id") Long id,
                                                 @RequestBody @Valid UpdateAccountDTO update)
            throws AccountServiceException {
        AccountDTO accountDTO = accountService.modify(id, update);
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);

    }

    @PatchMapping(value = "/{id}/branch/{branchId}")
    public ResponseEntity<BranchDTO> modifyAccountBranch(@PathVariable(name = "id") Long id,
                                                         @PathVariable(name = "branchId") Long branchId)
            throws AccountServiceException {
        BranchDTO branchDTO = accountService.modifyAccountBranch(id, branchId);
        return new ResponseEntity<>(branchDTO, HttpStatus.OK);

    }

    @PatchMapping(value = "/{id}/tax-identity")
    public ResponseEntity<TaxIdentityDTO> ModifyAccountTaxIdentity(@PathVariable(name = "id") Long id,
                                                                   @RequestParam Long taxIdentityId)
            throws AccountServiceException {
        TaxIdentityDTO taxIdentityDTO = accountService.modifyAccountTaxIdentity(id, taxIdentityId);
        return new ResponseEntity<>(taxIdentityDTO, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}/contacts")
    public ResponseEntity<Set<ContactDTO>> ModifyAccountContacts(@PathVariable(name = "id") Long id,
                                                                 @RequestParam Set<Long> contactIds)
            throws AccountServiceException {
        Set<ContactDTO> contactDTO = accountService.modifyAccountContacts(id, contactIds);
        return new ResponseEntity<>(contactDTO, HttpStatus.OK);

    }

    @PatchMapping(value = "/{id}/address")
    public ResponseEntity<Set<AddressDTO>> modifyAccountAddresses(@PathVariable(name = "id") Long id,
                                                                  @RequestParam Set<Long> addresses)
            throws AccountServiceException {
        Set<AddressDTO> addressDTO = accountService.modifyAccountAddresses(id, addresses);
        return new ResponseEntity<>(addressDTO, HttpStatus.OK);

    }

    @PatchMapping(value = "/{id}/parent")
    public ResponseEntity<AccountDTO> modifyAccountParent(@PathVariable(name = "id") Long id,
                                                          @RequestParam Long parent)
            throws AccountServiceException {
        AccountDTO accountDTO = accountService.modifyAccountParent(id, parent);
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }


    @GetMapping(value = "/existsByRegisteredPhone")
    public boolean existsByRegisteredPhone(@RequestParam String registeredPhone) throws AccountServiceException {
        return accountService.existsByRegisteredPhone(registeredPhone);
    }

    @GetMapping(value = "/existsByEmail")
    public boolean existsByEmail(@RequestParam String email) throws AccountServiceException{
        return accountService.existsByEmail(email);
    }






}
