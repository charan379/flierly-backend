package com.ctytech.flierly.account.service;

import com.ctytech.flierly.FlierlyException;
import com.ctytech.flierly.account.dto.AccountDTO;
import com.ctytech.flierly.account.dto.UpdateAccountDTO;
import com.ctytech.flierly.account.entity.Account;
import com.ctytech.flierly.account.exception.AccountServiceException;
import com.ctytech.flierly.account.mapper.AccountMapper;
import com.ctytech.flierly.account.repository.AccountRepository;
import com.ctytech.flierly.address.dto.AddressDTO;
import com.ctytech.flierly.contact.dto.ContactDTO;
import com.ctytech.flierly.organization.dto.BranchDTO;
import com.ctytech.flierly.taxation.dto.TaxIdentityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service(value = "/accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public AccountDTO save(AccountDTO accountDTO) throws AccountServiceException {

        if (existsByRegisteredPhone(accountDTO.getRegisteredPhone()))
            throw new AccountServiceException("AccountService.REGISTERED_PHONE_ALREADY_EXISTS");

        if (existsByEmail(accountDTO.getEmail()))
            throw new AccountServiceException("AccountService.EMAIL_ALREADY_EXISTS");

        return accountMapper.toDTO(accountRepository.save(accountMapper.toEntity(accountDTO)));
    }

    @Override
    public AccountDTO fetch(Long id, String... includeDTOs) throws AccountServiceException {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountServiceException("AccountService.NOT_FOUND"));
        return accountMapper.toDTO(account, includeDTOs);
    }

    @Override
    public Set<AccountDTO> fetchAllByIds(Set<Long> ids, String... includeDTOs) {
        return accountRepository.findAllById(ids).stream().map(account -> accountMapper.toDTO(account, includeDTOs)).collect(Collectors.toSet());
    }

    @Override
    public AccountDTO modify(Long id, UpdateAccountDTO update) throws AccountServiceException {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountServiceException("AccountService.NOT_FOUND"));
        // update isVip
        if (update.getIsVip() != null) account.setIsVip(update.getIsVip());
        // update isKey
        if (update.getIsKey() != null) account.setIsKey(update.getIsKey());
        // update name if provided and not same as existing
        if (update.getName() != null && !account.getName().equalsIgnoreCase(update.getName()))
            account.setName(update.getName());
        // update account type
        if (update.getAccountTypeId() != null)
            account.setAccountType(accountMapper.idToAccountType(update.getAccountTypeId()));
        // update account subtype
        if (update.getAccountSubtypeId() != null)
            account.setAccountSubtype(accountMapper.idToAccountSubtype(update.getAccountTypeId()));
        // return updated account
        return accountMapper.toDTO(accountRepository.save(account));
    }

    @Override
    public BranchDTO modifyAccountBranch(Long accountId, Long branchId) throws AccountServiceException {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountServiceException("AccountService.NOT_FOUND"));
        try {
            BranchDTO branchDTO = accountMapper.idToBranchDTO(branchId);
            account.setBranchId(branchDTO.getId());
            accountRepository.save(account);
            return branchDTO;
        } catch (FlierlyException e) {
            throw new AccountServiceException(e.getMessage());
        }
    }

    @Override
    public TaxIdentityDTO modifyAccountTaxIdentity(Long accountId, Long taxIdentityId) throws AccountServiceException {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountServiceException("AccountService.NOT_FOUND"));
        try {
            TaxIdentityDTO taxIdentityDTO = accountMapper.idTotaxIdentityDTO(taxIdentityId);
            account.setTaxIdentityId(taxIdentityDTO.getId());
            accountRepository.save(account);
            return taxIdentityDTO;
        } catch (FlierlyException e) {
            throw new AccountServiceException(e.getMessage());
        }
    }

    @Override
    public Set<ContactDTO> modifyAccountContacts(Long accountId, Set<Long> contactIds) throws AccountServiceException {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountServiceException("AccountService.NOT_FOUND"));
        // get existing contacts
        Set<Long> existingContacts = account.getContactIds();
        // contact ids to be removed
        Set<Long> idsTobeRemoved = existingContacts.stream().filter(id -> !contactIds.contains(id)).collect(Collectors.toSet());
        // contact ids to be added
        Set<Long> idsToBeAdded = contactIds.stream().filter(id -> !existingContacts.contains(id)).collect(Collectors.toSet());
        // Remove ids
        account.getContactIds().removeAll(idsTobeRemoved);
        // validate new contactIds
        Set<Long> validContactIdsToBeAdded = accountMapper.contactIdsToDTOs(idsToBeAdded).stream().map(ContactDTO::getId).collect(Collectors.toSet());
        // add valid contactIds to account contacts
        account.getContactIds().addAll(validContactIdsToBeAdded);
        return accountMapper.toDTO(accountRepository.save(account), "contacts").getContacts();
    }

    @Override
    public Set<AddressDTO> modifyAccountAddresses(Long accountId, Set<Long> addressIds) throws AccountServiceException {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountServiceException("AccountService.NOT_FOUND"));
        // get existing address
        Set<Long> existingAddressIds = account.getAddressIds();
        // address ids to be removed
        Set<Long> idsTobeRemoved = existingAddressIds.stream().filter(id -> !addressIds.contains(id)).collect(Collectors.toSet());
        // address ids to be added
        Set<Long> idsToBeAdded = addressIds.stream().filter(id -> !existingAddressIds.contains(id)).collect(Collectors.toSet());
        // Remove ids
        account.getAddressIds().removeAll(idsTobeRemoved);
        // validate new addressIds
        Set<Long> validAddressIdsToBeAdded = accountMapper.addressIdsToDTOs(idsToBeAdded).stream().map(AddressDTO::getId).collect(Collectors.toSet());
        // add valid addressIds to account addresses
        account.getAddressIds().addAll(validAddressIdsToBeAdded);
        return accountMapper.toDTO(accountRepository.save(account), "addresses").getAddresses();
    }

    @Override
    public AccountDTO modifyAccountParent(Long accountId, Long parentAccountId) throws AccountServiceException {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountServiceException("AccountService.NOT_FOUND"));
        Account parentAccount = accountRepository.findById(accountId).orElseThrow(() -> new AccountServiceException("AccountService.INVALID_PARENT"));
        account.setParentAccount(parentAccount);
        accountRepository.save(account);
        return accountMapper.toDTO(parentAccount);
    }

    @Override
    public Boolean existsByRegisteredPhone(String registeredPhone) {
        return accountRepository.existsByRegisteredPhone(registeredPhone);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }
}
