package com.ctytech.flierly.account.mapper;

import com.ctytech.flierly.account.dto.AccountTypeDTO;
import com.ctytech.flierly.account.entity.AccountType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountTypeMapper {
    @Autowired
    private ModelMapper modelMapper;

    public AccountTypeDTO toDTO(AccountType accountType) {
        if (accountType == null) return null;
        return modelMapper.map(accountType, AccountTypeDTO.class);
    }

    public AccountType toEntity(AccountTypeDTO accountTypeDTO) {
        return modelMapper.map(accountTypeDTO, AccountType.class);
    }
}
