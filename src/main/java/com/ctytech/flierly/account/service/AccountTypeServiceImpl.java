package com.ctytech.flierly.account.service;

import com.ctytech.flierly.account.dto.AccountTypeDTO;
import com.ctytech.flierly.account.entity.AccountSubtype;
import com.ctytech.flierly.account.entity.AccountType;
import com.ctytech.flierly.account.exception.AccountServiceException;
import com.ctytech.flierly.account.mapper.AccountTypeMapper;
import com.ctytech.flierly.account.repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service(value = "accountTypeService")
@Transactional
public class AccountTypeServiceImpl implements AccountTypeService {
    @Autowired
    private AccountTypeRepository accountTypeRepository;
    @Autowired
    private AccountTypeMapper accountTypeMapper;

    @Override
    public AccountTypeDTO save(AccountTypeDTO accountTypeDTO) throws AccountServiceException {
        // Check if name already exists
        if (existsByName(accountTypeDTO.getName()))
            throw new AccountServiceException("AccountType.NAME_ALREADY_EXISTS");
        // Save new account type
        return accountTypeMapper.toDTO(accountTypeRepository.save(accountTypeMapper.toEntity(accountTypeDTO)), "subtypes");
    }

    @Override
    public AccountTypeDTO fetch(Long id, String... includeDTOs) throws AccountServiceException {
        AccountType accountType = accountTypeRepository.findById(id).orElseThrow(() -> new AccountServiceException("AccountType.NOT_FOUND"));
        return accountTypeMapper.toDTO(accountType, includeDTOs);
    }

    @Override
    public List<AccountTypeDTO> fetchAll(String... includeDTOs) {
        return accountTypeRepository.findAll().stream().map(accountType -> accountTypeMapper.toDTO(accountType, includeDTOs)).toList();
    }

    @Override
    public AccountTypeDTO modify(Long id, AccountTypeDTO update) throws AccountServiceException {
        AccountType accountType = accountTypeRepository.findById(id).orElseThrow(() -> new AccountServiceException("AccountType.NOT_FOUND"));
        // Check if new name and old name are different, if same then no need to do nay update.
        if (accountType.getName().equalsIgnoreCase(update.getName())) return update;
        // Check if new name already exists
        if (existsByName(update.getName())) throw new AccountServiceException("AccountType.NAME_ALREADY_EXISTS");
        // Do update
        accountType.setName(update.getName());
        return accountTypeMapper.toDTO(accountTypeRepository.save(accountType));
    }

    @Override
    public AccountTypeDTO modifySubtypes(Long id, Set<Long> subtypesIds) throws AccountServiceException {
        AccountType accountType = accountTypeRepository.findById(id).orElseThrow(() -> new AccountServiceException("AccountType.NOT_FOUND"));
        // Check if provided subtypes set is not null
        if (subtypesIds != null) {
            // get ids of all subtypes available accountType
            Set<Long> existingSubtypeIds = accountType.getSubtypes().stream().map(AccountSubtype::getId).collect(Collectors.toSet());
            // get ids which are available in accountType entity but not in subTypesIds set, those will be de-mapped form accountType entity
            Set<Long> subTypesToBeDeMapped = existingSubtypeIds.stream().filter(exId -> subtypesIds.stream().noneMatch(upId -> upId.equals(exId))).collect(Collectors.toSet());
            // get ids which are not available in accountType entity but available in subTypesIds set, those will be mapped to accountType entity
            Set<Long> subTypesToBeMapped = subtypesIds.stream().filter(upId -> !existingSubtypeIds.contains(upId)).collect(Collectors.toSet());
            // For each id in subTypesToBeMapped check if it exists in db then map it to accountType
            accountType.getSubtypes().addAll(accountTypeMapper.subtypeIdsToSubtypes(subTypesToBeMapped));
            // Remove subtypes which are to be De-mapped form accountType
            accountType.getSubtypes().removeIf(accountSubtype -> subTypesToBeDeMapped.contains(accountSubtype.getId()));
            // Return new accountType with updated subtypes set
            return accountTypeMapper.toDTO(accountTypeRepository.save(accountType), "subtypes");
        } else
            // if provided subtypes set is a null then just return accountType fetched from db
            return accountTypeMapper.toDTO(accountType);
    }


    @Override
    public Boolean existsByName(String name) {
        return accountTypeRepository.existsByName(name);
    }
}
