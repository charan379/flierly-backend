package com.ctytech.flierly.organization.mapper;

import com.ctytech.flierly.FlierlyException;
import com.ctytech.flierly.address.dto.AddressDTO;
import com.ctytech.flierly.address.service.AddressService;
import com.ctytech.flierly.organization.dto.BranchDTO;
import com.ctytech.flierly.organization.entity.Branch;
import com.ctytech.flierly.taxation.dto.TaxIdentityDTO;
import com.ctytech.flierly.taxation.service.TaxIdentityService;
import com.ctytech.flierly.utility.ModelMappingUtils;
import jakarta.annotation.PostConstruct;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BranchMapper {

    private ModelMapper modelMapper;

    @Autowired
    private ModelMappingUtils modelMappingUtils;
    @Autowired
    private TaxIdentityService taxIdentityService;
    @Autowired
    private AddressService addressService;

    @PostConstruct
    public void init() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setImplicitMappingEnabled(false);

        // BranchDTO -> Branch entity mapping
        modelMapper.createTypeMap(BranchDTO.class, Branch.class)
                .addMappings(mapper -> mapper.when(Conditions.isNotNull()).map(BranchDTO::getId, Branch::setId))
                .addMapping(BranchDTO::getName, Branch::setName)
                .addMapping(BranchDTO::getIsActive, Branch::setIsActive)
                .addMapping(BranchDTO::getAddressId, Branch::setAddressId)
                .addMapping(BranchDTO::getTaxIdentityId, Branch::setTaxIdentityId)
                .addMapping(BranchDTO::getPhone, Branch::setPhone)
                .addMapping(BranchDTO::getAlternatePhone, Branch::setAlternatePhone)
                .addMapping(BranchDTO::getEmail, Branch::setEmail);

        // Branch Entity -> BranchDTO mapping
        modelMapper.createTypeMap(Branch.class, BranchDTO.class)
                .addMapping(Branch::getId, BranchDTO::setId)
                .addMapping(Branch::getName, BranchDTO::setName)
                .addMapping(Branch::getIsActive, BranchDTO::setIsActive)
                .addMapping(Branch::getAddressId, BranchDTO::setAddressId)
                .addMapping(Branch::getTaxIdentityId, BranchDTO::setTaxIdentityId)
                .addMapping(Branch::getPhone, BranchDTO::setPhone)
                .addMapping(Branch::getAlternatePhone, BranchDTO::setAlternatePhone)
                .addMapping(Branch::getEmail, BranchDTO::setEmail);
    }

    private final Converter<Long, TaxIdentityDTO> taxIdentityIdToDtoConverter = mappingContext -> {
        if (mappingContext.getSource() != null) {
            try {
                return taxIdentityService.fetch(mappingContext.getSource(), "gst_registration_address");
            } catch (FlierlyException e) {
                return new TaxIdentityDTO();
            }
        }
        return null;
    };

    private final Converter<Long, AddressDTO> addressIdToAddressConverter = mappingContext -> {
        if (mappingContext.getSource() != null) {
            try {
                return addressService.fetch(mappingContext.getSource());
            } catch (FlierlyException e) {
                return new AddressDTO();
            }
        }
        return null;
    };

    public BranchDTO toDTO(Branch branch, String... includesDTOs) {
        if (branch == null) return null;
        // Include Address DTO based on includes
        modelMapper.getTypeMap(Branch.class, BranchDTO.class)
                .addMappings(mapper -> mapper
                        .when(modelMappingUtils.canInclude("address", includesDTOs))
                        .using(addressIdToAddressConverter)
                        .map(Branch::getAddressId, BranchDTO::setAddress));
        // Include TaxIdentity DTO based on includes
        modelMapper.getTypeMap(Branch.class, BranchDTO.class)
                .addMappings(mapper -> mapper
                        .when(modelMappingUtils.canInclude("tax_identity", includesDTOs))
                        .using(taxIdentityIdToDtoConverter)
                        .map(Branch::getTaxIdentityId, BranchDTO::setTaxIdentity));
        // return branchDTO
        return modelMapper.map(branch, BranchDTO.class);
    }

    public Branch toEntity(BranchDTO branchDTO) {
        return modelMapper.map(branchDTO, Branch.class);
    }
}
