package com.ctytech.flierly.account.service;

import com.ctytech.flierly.account.dto.AccountSubtypeDTO;
import com.ctytech.flierly.account.entity.AccountSubtype;
import com.ctytech.flierly.account.exception.AccountServiceException;

import java.util.List;
import java.util.Set;

public interface AccountSubtypeService {

    AccountSubtypeDTO save(AccountSubtypeDTO accountSubtypeDTO) throws AccountServiceException;

    AccountSubtypeDTO fetch(Long id) throws AccountServiceException;

    List<AccountSubtypeDTO> fetchAll();

    List<AccountSubtype> fetchByIds(Set<Long> ids);

    AccountSubtypeDTO modify(Long id, AccountSubtypeDTO update) throws AccountServiceException;

    Boolean existsByName(String name);
}
