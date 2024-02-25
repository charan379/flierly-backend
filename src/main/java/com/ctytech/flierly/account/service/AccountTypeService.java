package com.ctytech.flierly.account.service;

import com.ctytech.flierly.account.dto.AccountTypeDTO;
import com.ctytech.flierly.account.exception.AccountServiceException;

import java.util.List;
import java.util.Set;

public interface AccountTypeService {

    AccountTypeDTO save(AccountTypeDTO accountTypeDTO) throws AccountServiceException;

    AccountTypeDTO fetch(Long id, String... includeDTOs) throws AccountServiceException;

    List<AccountTypeDTO> fetchAll(String... includeDTOs);

    AccountTypeDTO modify(Long id, AccountTypeDTO update) throws AccountServiceException;

    AccountTypeDTO modifySubtypes(Long id, Set<Long> subtypesIds) throws AccountServiceException;

    Boolean existsByName(String name);
}
