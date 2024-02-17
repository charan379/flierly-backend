package com.ctytech.flierly.account.service;

import com.ctytech.flierly.account.dto.AccountSubtypeDTO;
import com.ctytech.flierly.account.exception.AccountServiceException;

import java.util.List;

public interface AccountSubtypeService {

    AccountSubtypeDTO save(AccountSubtypeDTO accountSubtypeDTO) throws AccountServiceException;

    AccountSubtypeDTO fetch(Long id) throws AccountServiceException;

    List<AccountSubtypeDTO> fetchAll();

    AccountSubtypeDTO modify(Long id, AccountSubtypeDTO update) throws AccountServiceException;

    Boolean existsByName(String name);
}
