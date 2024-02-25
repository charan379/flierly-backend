package com.ctytech.flierly.account.mapper;

import com.ctytech.flierly.FlierlyException;
import com.ctytech.flierly.account.dto.AccountDTO;
import com.ctytech.flierly.account.dto.AccountSubtypeDTO;
import com.ctytech.flierly.account.dto.AccountTypeDTO;
import com.ctytech.flierly.account.entity.Account;
import com.ctytech.flierly.account.entity.AccountSubtype;
import com.ctytech.flierly.account.entity.AccountType;
import com.ctytech.flierly.account.exception.AccountServiceException;
import com.ctytech.flierly.account.service.AccountSubtypeService;
import com.ctytech.flierly.account.service.AccountTypeService;
import com.ctytech.flierly.address.dto.AddressDTO;
import com.ctytech.flierly.address.service.AddressService;
import com.ctytech.flierly.contact.dto.ContactDTO;
import com.ctytech.flierly.contact.service.ContactService;
import com.ctytech.flierly.organization.dto.BranchDTO;
import com.ctytech.flierly.organization.service.BranchService;
import com.ctytech.flierly.taxation.dto.TaxIdentityDTO;
import com.ctytech.flierly.taxation.service.TaxIdentityService;
import com.ctytech.flierly.utility.ModelMappingUtils;
import jakarta.annotation.PostConstruct;
import org.modelmapper.Converter;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AccountMapper {

    private ModelMapper modelMapper;
    @Autowired
    private ModelMappingUtils modelMappingUtils;
    @Autowired
    private AccountTypeService accountTypeService;
    @Autowired
    private AccountSubtypeService accountSubtypeService;
    @Autowired
    private BranchService branchService;
    @Autowired
    private TaxIdentityService taxIdentityService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private AddressService addressService;

    @PostConstruct
    public void init() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setImplicitMappingEnabled(false);

        // Account -> AccountDTO mapping
        modelMapper.createTypeMap(Account.class, AccountDTO.class)
                .addMapping(Account::getId, AccountDTO::setId)
                .addMapping(Account::getIsVip, AccountDTO::setIsVip)
                .addMapping(Account::getIsKey, AccountDTO::setIsKey)
                .addMapping(Account::getName, AccountDTO::setName)
                .addMapping(Account::getRegisteredPhone, AccountDTO::setRegisteredPhone)
                .addMapping(Account::getAlternatePhone, AccountDTO::setAlternatePhone)
                .addMapping(Account::getEmail, AccountDTO::setEmail)
                .addMapping(Account::getAccountType, AccountDTO::setAccountType)
                .addMapping(Account::getAccountSubtype, AccountDTO::setAccountSubtype)
                .addMappings(mapper -> mapper
                        .using(accountTypeToIdConverter)
                        .map(Account::getAccountType, AccountDTO::setAccountTypeId))
                .addMappings(mapper -> mapper
                        .using(accountSubtypeToIdConverter)
                        .map(Account::getAccountType, AccountDTO::setAccountSubtypeId))
                .addMapping(Account::getBranchId, AccountDTO::setBranchId)
                .addMapping(Account::getTaxIdentityId, AccountDTO::setTaxIdentityId)
                .addMapping(Account::getContactIds, AccountDTO::setContactIds)
                .addMapping(Account::getAddressIds, AccountDTO::setAddressIds);


        // AccountDTO -> Account mapping
        modelMapper.createTypeMap(AccountDTO.class, Account.class)
                .addMapping(AccountDTO::getId, Account::setId)
                .addMapping(AccountDTO::getIsVip, Account::setIsVip)
                .addMapping(AccountDTO::getIsKey, Account::setIsKey)
                .addMapping(AccountDTO::getName, Account::setName)
                .addMapping(AccountDTO::getRegisteredPhone, Account::setRegisteredPhone)
                .addMapping(AccountDTO::getAlternatePhone, Account::setAlternatePhone)
                .addMapping(AccountDTO::getEmail, Account::setEmail)
                .addMappings(mapper -> mapper
                        .using(accountTypeIDtoEntityConverter)
                        .map(AccountDTO::getAccountTypeId, Account::setAccountType))
                .addMappings(mapper -> mapper
                        .using(accountSubtypeIdToEntityConverter)
                        .map(AccountDTO::getAccountSubtypeId, Account::setAccountSubtype))
                .addMapping(AccountDTO::getBranchId, Account::setBranchId)
                .addMapping(AccountDTO::getTaxIdentityId, Account::setTaxIdentityId)
                .addMapping(AccountDTO::getContactIds, Account::setContactIds)
                .addMapping(AccountDTO::getAddressIds, Account::setAddressIds);


        // AccountType Mapping
        modelMapper.createTypeMap(AccountType.class, AccountTypeDTO.class)
                .addMapping(AccountType::getId, AccountTypeDTO::setId)
                .addMapping(AccountType::getName, AccountTypeDTO::setName);
        modelMapper.createTypeMap(AccountTypeDTO.class, AccountType.class)
                .addMapping(AccountTypeDTO::getId, AccountType::setId)
                .addMapping(AccountTypeDTO::getName, AccountType::setName);

        // AccountSubType Mappings
        modelMapper.createTypeMap(AccountSubtypeDTO.class, AccountSubtype.class).implicitMappings();
        modelMapper.createTypeMap(AccountSubtype.class, AccountSubtypeDTO.class).implicitMappings();
    }

    private final Converter<Long, AccountType> accountTypeIDtoEntityConverter = mappingContext -> {
        if (mappingContext.getSource() != null) {
            try {
                AccountTypeDTO accountTypeDTO = accountTypeService.fetch(mappingContext.getSource());
                return modelMapper.map(accountTypeDTO, AccountType.class);
            } catch (AccountServiceException e) {
                List<ErrorMessage> errorMessages = new ArrayList<>();
                errorMessages.add(new ErrorMessage(e.getMessage()));
                throw new MappingException(errorMessages);
            }
        }
        return null;
    };

    private final Converter<Long, AccountSubtype> accountSubtypeIdToEntityConverter = mappingContext -> {
        if (mappingContext.getSource() != null) {
            try {
                AccountSubtypeDTO accountSubtypeDTO = accountSubtypeService.fetch(mappingContext.getSource());
                return modelMapper.map(accountSubtypeDTO, AccountSubtype.class);
            } catch (AccountServiceException e) {
                List<ErrorMessage> errorMessages = new ArrayList<>();
                errorMessages.add(new ErrorMessage(e.getMessage()));
                throw new MappingException(errorMessages);
            }
        }
        return null;
    };

    private final Converter<Long, BranchDTO> branchIdToDTOConverter = mappingContext -> {
        if (mappingContext.getSource() != null) {
            try {
                return branchService.fetch(mappingContext.getSource());
            } catch (FlierlyException e) {
                return null;
            }
        }
        return null;
    };

    private final Converter<Long, TaxIdentityDTO> taxIdentityIdToDTOConverter = mappingContext -> {
        if (mappingContext.getSource() != null) {
            try {
                return taxIdentityService.fetch(mappingContext.getSource());
            } catch (FlierlyException e) {
                return null;
            }
        }
        return null;
    };

    private final Converter<Set<Long>, Set<ContactDTO>> contactIdsToDTOsConverter = mappingContext -> {
        if (mappingContext.getSource() != null)
            return contactService.fetchAllByIds(mappingContext.getSource());
        return new HashSet<>();
    };

    private final Converter<Set<Long>, Set<AddressDTO>> addressIdsToDTOsConverter = mappingContext -> {
        if (mappingContext.getSource() != null)
            return addressService.fetchAllByIds(mappingContext.getSource());
        return new HashSet<>();
    };

    private final Converter<AccountType, Long> accountTypeToIdConverter = mappingContext -> {
        if (mappingContext.getSource() != null)
            return mappingContext.getSource().getId();
        return null;
    };

    private final Converter<AccountSubtype, Long> accountSubtypeToIdConverter = mappingContext -> {
        if (mappingContext.getSource() != null)
            return mappingContext.getSource().getId();
        return null;
    };

    private AccountDTO toDTO(Account account, String... includeDTOs) {
        if (account == null) return null;
        modelMapper.getTypeMap(Account.class, AccountDTO.class)
                // Include BranchDTO based on includeDTOs
                .addMappings(mapper -> mapper
                        .when(modelMappingUtils.canInclude("branch", includeDTOs))
                        .using(branchIdToDTOConverter)
                        .map(Account::getBranchId, AccountDTO::setBranch))
                // Include TaxIdentity based on includeDTOs
                .addMappings(mapper -> mapper
                        .when(modelMappingUtils.canInclude("tax_identity", includeDTOs))
                        .using(taxIdentityIdToDTOConverter)
                        .map(Account::getTaxIdentityId, AccountDTO::setTaxIdentity))
                // Include Contacts based on includeDTOs
                .addMappings(mapper -> mapper
                        .when(modelMappingUtils.canInclude("contacts", includeDTOs))
                        .using(contactIdsToDTOsConverter)
                        .map(Account::getContactIds, AccountDTO::setContacts))
                // Include Address based on includeDTOs
                .addMappings(mapper -> mapper
                        .when(modelMappingUtils.canInclude("addresses", includeDTOs))
                        .using(addressIdsToDTOsConverter)
                        .map(Account::getAddressIds, AccountDTO::setAddresses));
        return modelMapper.map(account, AccountDTO.class);
    }

    public Account toEntity(AccountDTO accountDTO) {
        return modelMapper.map(accountDTO, Account.class);
    }
}
