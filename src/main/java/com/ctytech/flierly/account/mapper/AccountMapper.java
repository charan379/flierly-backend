package com.ctytech.flierly.account.mapper;

import com.ctytech.flierly.account.dto.AccountDTO;
import com.ctytech.flierly.account.entity.Account;
import com.ctytech.flierly.address.mapper.AddressMapper;
import com.ctytech.flierly.contact.mapper.ContactMapper;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    @Autowired
    private ModelMapper modelMapper;
    @Getter
    @Autowired
    private AccountTypeMapper accountTypeMapper;
    @Getter
    @Autowired
    private AccountSubtypeMapper accountSubtypeMapper;
    @Getter
    @Autowired
    private ContactMapper contactMapper;
    @Getter
    @Setter
    private AddressMapper addressMapper;


    private AccountDTO toDTO(Account account) {
        if (account == null) return null;
        AccountDTO dto = new AccountDTO();
        dto.setId(account.getId());
        dto.setIsVip(account.getIsVip());
        dto.setIsKey(account.getIsKey());
        dto.setName(account.getName());
        dto.setRegisteredPhone(account.getRegisteredPhone());
        dto.setAlternatePhone(account.getAlternatePhone());
        dto.setEmail(account.getEmail());
        dto.setAccountType(accountTypeMapper.toDTO(account.getAccountType()));
        dto.setAccountSubtype(accountSubtypeMapper.toDTO(account.getAccountSubtype()));
//        dto.setContacts();
        return dto;
    }

    public Account toEntity(AccountDTO accountDTO) {
        return modelMapper.map(accountDTO, Account.class);
    }
}
