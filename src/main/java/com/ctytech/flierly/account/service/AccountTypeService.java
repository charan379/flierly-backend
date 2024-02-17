package com.ctytech.flierly.account.service;

import com.ctytech.flierly.account.dto.AccountTypeDTO;
import com.ctytech.flierly.account.exception.AccountServiceException;

import java.util.List;

public interface AccountTypeService {

    AccountTypeDTO save(AccountTypeDTO accountTypeDTO) throws AccountServiceException;

    AccountTypeDTO fetch(Long id) throws AccountServiceException;

    List<AccountTypeDTO> fetchAll();

    AccountTypeDTO modify(Long id, AccountTypeDTO update) throws AccountServiceException;

    Boolean existsByName(String name);
}
