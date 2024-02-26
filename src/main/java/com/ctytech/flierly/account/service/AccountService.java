package com.ctytech.flierly.account.service;

import com.ctytech.flierly.account.dto.AccountDTO;
import com.ctytech.flierly.account.dto.UpdateAccountDTO;
import com.ctytech.flierly.account.exception.AccountServiceException;
import com.ctytech.flierly.address.dto.AddressDTO;
import com.ctytech.flierly.contact.dto.ContactDTO;
import com.ctytech.flierly.organization.dto.BranchDTO;
import com.ctytech.flierly.taxation.dto.TaxIdentityDTO;

import java.util.Set;

public interface AccountService {

    AccountDTO save(AccountDTO accountDTO) throws AccountServiceException;

    AccountDTO fetch(Long id, String... includeDTOs) throws AccountServiceException;

    Set<AccountDTO> fetchAllByIds(Set<Long> ids, String... includeDTOs);

    AccountDTO modify(Long id, UpdateAccountDTO update) throws AccountServiceException;

    BranchDTO modifyAccountBranch(Long accountId, Long branchId) throws AccountServiceException;

    TaxIdentityDTO modifyAccountTaxIdentity(Long accountId, Long taxIdentityId) throws AccountServiceException;

    Set<ContactDTO> modifyAccountContacts(Long accountId, Set<Long> contactIds) throws AccountServiceException;

    Set<AddressDTO> modifyAccountAddresses(Long accountId, Set<Long> addressIds) throws AccountServiceException;

    AccountDTO modifyAccountParent(Long accountId, Long parentAccountId) throws AccountServiceException;

    Boolean existsByRegisteredPhone(String registeredPhone);

    Boolean existsByEmail(String email);
}
