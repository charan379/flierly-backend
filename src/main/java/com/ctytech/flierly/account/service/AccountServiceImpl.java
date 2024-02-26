package com.ctytech.flierly.account.service;

import com.ctytech.flierly.account.dto.AccountDTO;
import com.ctytech.flierly.account.dto.UpdateAccountDTO;
import com.ctytech.flierly.account.entity.Account;
import com.ctytech.flierly.account.exception.AccountServiceException;
import com.ctytech.flierly.account.mapper.AccountMapper;
import com.ctytech.flierly.account.repository.AccountRepository;
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
    public Boolean existsByRegisteredPhone(String registeredPhone) {
        return accountRepository.existsByRegisteredPhone(registeredPhone);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }
}
