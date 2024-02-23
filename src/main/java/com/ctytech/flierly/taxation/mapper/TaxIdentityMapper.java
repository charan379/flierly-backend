package com.ctytech.flierly.taxation.mapper;

import com.ctytech.flierly.taxation.dto.TaxIdentityDTO;
import com.ctytech.flierly.taxation.entity.TaxIdentity;
import com.ctytech.flierly.utility.ModelMappingUtils;
import jakarta.annotation.PostConstruct;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaxIdentityMapper {

    private ModelMapper modelMapper;

    @Autowired
    private ModelMappingUtils modelMappingUtils;

    @PostConstruct
    public void init() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setImplicitMappingEnabled(false);

        // TaxIdentityDTO ->  TaxIdentity mapping
        modelMapper.createTypeMap(TaxIdentityDTO.class, TaxIdentity.class)
                .addMappings(mapper -> mapper.when(Conditions.isNotNull()).map(TaxIdentityDTO::getId, TaxIdentity::setId))
                .addMapping(TaxIdentityDTO::getIsActive, TaxIdentity::setIsActive)
                .addMapping(TaxIdentityDTO::getGst, TaxIdentity::setGst)
                .addMapping(TaxIdentityDTO::getGstRegistrationDate, TaxIdentity::setGstRegistrationDate)
                .addMapping(TaxIdentityDTO::getGstVerified, TaxIdentity::setGstVerified)
                .addMapping(TaxIdentityDTO::getGstRegistrationAddressId, TaxIdentity::setGstRegistrationAddressId)
                .addMapping(TaxIdentityDTO::getPan, TaxIdentity::setPan)
                .addMapping(TaxIdentityDTO::getPanVerified, TaxIdentity::setPanVerified)
                .addMapping(TaxIdentityDTO::getVat, TaxIdentity::setVat)
                .addMapping(TaxIdentityDTO::getTin, TaxIdentity::setTin);

        // TaxIdentity ->  TaxIdentityDTO mapping
        modelMapper.createTypeMap(TaxIdentity.class, TaxIdentityDTO.class)
                .addMapping(TaxIdentity::getId, TaxIdentityDTO::setId)
                .addMapping(TaxIdentity::getIsActive, TaxIdentityDTO::setIsActive)
                .addMapping(TaxIdentity::getGst, TaxIdentityDTO::setGst)
                .addMapping(TaxIdentity::getGstRegistrationDate, TaxIdentityDTO::setGstRegistrationDate)
                .addMapping(TaxIdentity::getGstVerified, TaxIdentityDTO::setGstVerified)
                .addMapping(TaxIdentity::getGstRegistrationAddressId, TaxIdentityDTO::setGstRegistrationAddressId)
                .addMapping(TaxIdentity::getPan, TaxIdentityDTO::setPan)
                .addMapping(TaxIdentity::getPanVerified, TaxIdentityDTO::setPanVerified)
                .addMapping(TaxIdentity::getVat, TaxIdentityDTO::setVat)
                .addMapping(TaxIdentity::getTin, TaxIdentityDTO::setTin);
    }

    public TaxIdentityDTO toDTO(TaxIdentity taxIdentity, String... includesDTOs) {
        // If null return null
        if (taxIdentity == null) return null;
        // Include GST Address DTO based on includes
        modelMapper.getTypeMap(TaxIdentity.class, TaxIdentityDTO.class)
                .addMappings(mapper -> mapper
                        .when(modelMappingUtils.canInclude("gst_registration_address", includesDTOs))
                        .using(modelMappingUtils.addressIdToAddressConverter)
                        .map(TaxIdentity::getGstRegistrationAddressId, TaxIdentityDTO::setGstRegistrationAddress));
        // return taxIdentityDTO
        return modelMapper.map(taxIdentity, TaxIdentityDTO.class);
    }

    public TaxIdentity toEntity(TaxIdentityDTO taxIdentityDTO) {
        return modelMapper.map(taxIdentityDTO, TaxIdentity.class);
    }
}
