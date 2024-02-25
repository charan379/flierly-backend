package com.ctytech.flierly.account.mapper;

import com.ctytech.flierly.account.dto.AccountDTO;
import com.ctytech.flierly.account.entity.Account;
import com.ctytech.flierly.address.mapper.AddressMapper;
import com.ctytech.flierly.contact.mapper.ContactMapper;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void init() {


    }


    private AccountDTO toDTO(Account account) {
        if (account == null) return null;
        return modelMapper.map(account, AccountDTO.class);
    }

    public Account toEntity(AccountDTO accountDTO) {
        return modelMapper.map(accountDTO, Account.class);
    }
}
