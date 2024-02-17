package com.ctytech.flierly.account.service;

import com.ctytech.flierly.account.dto.AccountSubtypeDTO;
import com.ctytech.flierly.account.entity.AccountSubtype;
import com.ctytech.flierly.account.exception.AccountServiceException;
import com.ctytech.flierly.account.mapper.AccountSubtypeMapper;
import com.ctytech.flierly.account.repository.AccountSubtypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "accountSubtypeService")
public class AccountSubtypeServiceImpl implements AccountSubtypeService {

    @Autowired
    private AccountSubtypeRepository accountSubtypeRepository;
    @Autowired
    private AccountSubtypeMapper accountSubtypeMapper;

    @Override
    public AccountSubtypeDTO save(AccountSubtypeDTO accountSubtypeDTO) throws AccountServiceException {
        // Check if name already exists
        if (existsByName(accountSubtypeDTO.getName()))
            throw new AccountServiceException("AccountSubtype.NAME_ALREADY_EXISTS");
        // Save new account subtype
        AccountSubtype accountSubtype = accountSubtypeMapper.toEntity(accountSubtypeDTO);
        return accountSubtypeMapper.toDTO(accountSubtypeRepository.save(accountSubtype));
    }

    @Override
    public AccountSubtypeDTO fetch(Long id) throws AccountServiceException {
        AccountSubtype subtype = accountSubtypeRepository.findById(id).orElseThrow(() -> new AccountServiceException("AccountSubtype.NOT_FOUND"));
        return accountSubtypeMapper.toDTO(subtype);
    }

    @Override
    public List<AccountSubtypeDTO> fetchAll() {
        return accountSubtypeRepository.findAll().stream().map(accountSubtype -> accountSubtypeMapper.toDTO(accountSubtype)).toList();
    }

    @Override
    public AccountSubtypeDTO modify(Long id, AccountSubtypeDTO update) throws AccountServiceException {
        AccountSubtype subtype = accountSubtypeRepository.findById(id).orElseThrow(() -> new AccountServiceException("AccountSubtype.NOT_FOUND"));
        // Check if new name and old name or different, if same then no need to do any update.
        if (subtype.getName().equalsIgnoreCase(update.getName())) return update;
        // Check if new name already exists
        if (existsByName(update.getName())) throw new AccountServiceException("AccountSubtype.NAME_ALREADY_EXISTS");
        // Do update
        subtype.setName(update.getName());
        return accountSubtypeMapper.toDTO(accountSubtypeRepository.save(subtype));
    }

    @Override
    public Boolean existsByName(String name) {
        return accountSubtypeRepository.existsByName(name);
    }
}
