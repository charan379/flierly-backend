package com.ctytech.flierly.account.service;

import com.ctytech.flierly.account.dto.AccountTypeDTO;
import com.ctytech.flierly.account.entity.AccountType;
import com.ctytech.flierly.account.exception.AccountServiceException;
import com.ctytech.flierly.account.mapper.AccountTypeMapper;
import com.ctytech.flierly.account.repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "accountTypeService")
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
        AccountType accountType = accountTypeMapper.toEntity(accountTypeDTO);
        return accountTypeMapper.toDTO(accountTypeRepository.save(accountType));
    }

    @Override
    public AccountTypeDTO fetch(Long id) throws AccountServiceException {
        AccountType accountType = accountTypeRepository.findById(id).orElseThrow(() -> new AccountServiceException("AccountType.NOT_FOUND"));
        return accountTypeMapper.toDTO(accountType);
    }

    @Override
    public List<AccountTypeDTO> fetchAll() {
        return accountTypeRepository.findAll().stream().map(accountType -> accountTypeMapper.toDTO(accountType)).toList();
    }

    @Override
    public AccountTypeDTO modify(Long id, AccountTypeDTO update) throws AccountServiceException {
        AccountType accountType = accountTypeRepository.findById(id).orElseThrow(() -> new AccountServiceException("AccountType.NOT_FOUND"));
        // Check if new name and old name are different, if same then no need to do nay update.
        if (!accountType.getName().equalsIgnoreCase(update.getName())) return update;
        // Check if new name already exists
        if (existsByName(update.getName())) throw new AccountServiceException("AccountType.NAME_ALREADY_EXISTS");
        // Do update
        accountType.setName(update.getName());
        return accountTypeMapper.toDTO(accountTypeRepository.save(accountType));
    }

    @Override
    public Boolean existsByName(String name) {
        return accountTypeRepository.existsByName(name);
    }
}
