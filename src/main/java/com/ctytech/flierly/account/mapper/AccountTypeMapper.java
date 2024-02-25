package com.ctytech.flierly.account.mapper;

import com.ctytech.flierly.account.dto.AccountSubtypeDTO;
import com.ctytech.flierly.account.dto.AccountTypeDTO;
import com.ctytech.flierly.account.entity.AccountSubtype;
import com.ctytech.flierly.account.entity.AccountType;
import com.ctytech.flierly.account.service.AccountSubtypeService;
import com.ctytech.flierly.utility.ModelMappingUtils;
import jakarta.annotation.PostConstruct;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AccountTypeMapper {

    private ModelMapper modelMapper;
    @Autowired
    private AccountSubtypeService accountSubtypeService;
    @Autowired
    private ModelMappingUtils modelMappingUtils;

    @PostConstruct
    public void init() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setImplicitMappingEnabled(false);

        // AccountTypeDTO -> AccountType Entity mapping
        modelMapper.createTypeMap(AccountTypeDTO.class, AccountType.class)
                .addMapping(AccountTypeDTO::getId, AccountType::setId)
                .addMapping(AccountTypeDTO::getName, AccountType::setName);

        // AccountType -> AccountTypeDTO mapping
        modelMapper.createTypeMap(AccountType.class, AccountTypeDTO.class)
                .addMapping(AccountType::getId, AccountTypeDTO::setId)
                .addMapping(AccountType::getName, AccountTypeDTO::setName);

        modelMapper.createTypeMap(AccountSubtypeDTO.class, AccountSubtype.class).implicitMappings();
        modelMapper.createTypeMap(AccountSubtype.class, AccountSubtypeDTO.class).implicitMappings();
    }

    private final Converter<Set<Long>, Set<AccountSubtype>> subTypeIdsToSubtypes = mappingContext -> {
        if (mappingContext.getSource() != null) {
            return new HashSet<>(accountSubtypeService.fetchByIds(mappingContext.getSource()));
        }
        return new HashSet<>();
    };

    private final Converter<Set<AccountSubtypeDTO>, Set<AccountSubtype>> subTypeDTOsToSubtypes = mappingContext -> {
        if (mappingContext.getSource() != null) {
            Set<Long> subTypeIds = mappingContext.getSource().stream().map(AccountSubtypeDTO::getId).collect(Collectors.toSet());
            return new HashSet<>(accountSubtypeService.fetchByIds(subTypeIds));
        }
        return new HashSet<>();
    };

    public AccountTypeDTO toDTO(AccountType accountType, String... includeDTOs) {
        if (accountType == null) return null;
        // Include SubType DTOs based on includesDTOs option
        modelMapper.getTypeMap(AccountType.class, AccountTypeDTO.class)
                .addMappings(mapper -> mapper
                        .when(modelMappingUtils.canInclude("subtypes", includeDTOs))
                        .map(AccountType::getSubtypes, AccountTypeDTO::setSubtypes)
                );
        return modelMapper.map(accountType, AccountTypeDTO.class);
    }

    public Set<AccountSubtype> subtypeIdsToSubtypes(Set<Long> subTypeIds) {
        if (subTypeIds == null) return new HashSet<>();
        return new HashSet<>(accountSubtypeService.fetchByIds(subTypeIds));
    }

    public AccountType toEntity(AccountTypeDTO accountTypeDTO) {
        // Include AccountSubtypes if provided
        modelMapper.getTypeMap(AccountTypeDTO.class, AccountType.class)
                .addMappings(mapper -> mapper
                        .when(Conditions.isNotNull())
                        .using(subTypeDTOsToSubtypes)
                        .map(AccountTypeDTO::getSubtypes, AccountType::setSubtypes)
                );
        // Return accountType entity
        return modelMapper.map(accountTypeDTO, AccountType.class);
    }
}
