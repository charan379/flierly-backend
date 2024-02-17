package com.ctytech.flierly.account.mapper;

import com.ctytech.flierly.account.dto.AccountSubtypeDTO;
import com.ctytech.flierly.account.entity.AccountSubtype;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountSubtypeMapper {
    @Autowired
    private ModelMapper modelMapper;

    public AccountSubtypeDTO toDTO(AccountSubtype accountSubtype) {
        if (accountSubtype == null) return null;
        return modelMapper.map(accountSubtype, AccountSubtypeDTO.class);
    }

    public AccountSubtype toEntity(AccountSubtypeDTO accountSubtypeDTO) {
        return modelMapper.map(accountSubtypeDTO, AccountSubtype.class);
    }
}
