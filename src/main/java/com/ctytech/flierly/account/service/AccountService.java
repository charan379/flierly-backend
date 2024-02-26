package com.ctytech.flierly.account.service;

import com.ctytech.flierly.account.dto.AccountDTO;
import com.ctytech.flierly.account.dto.UpdateAccountDTO;
import com.ctytech.flierly.account.exception.AccountServiceException;

import java.util.Set;

public interface AccountService {

    AccountDTO save(AccountDTO accountDTO) throws AccountServiceException;

    AccountDTO fetch(Long id, String... includeDTOs) throws AccountServiceException;

    Set<AccountDTO> fetchAllByIds(Set<Long> ids, String... includeDTOs);

    AccountDTO modify(Long id, UpdateAccountDTO update) throws AccountServiceException;

    Boolean existsByRegisteredPhone(String registeredPhone);

    Boolean existsByEmail(String email);
}
