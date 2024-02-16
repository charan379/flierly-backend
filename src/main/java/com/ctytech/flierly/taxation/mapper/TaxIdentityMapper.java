package com.ctytech.flierly.taxation.mapper;

import com.ctytech.flierly.address.mapper.AddressMapper;
import com.ctytech.flierly.taxation.dto.TaxIdentityDTO;
import com.ctytech.flierly.taxation.entity.TaxIdentity;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaxIdentityMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Getter
    @Autowired
    private AddressMapper addressMapper;

    public TaxIdentityDTO toDTO(TaxIdentity taxIdentity) {
        if (taxIdentity == null) return null;
        TaxIdentityDTO dto = new TaxIdentityDTO();
        dto.setId(taxIdentity.getId());
        dto.setIsActive(taxIdentity.getIsActive());
        dto.setGst(taxIdentity.getGst());
        dto.setGstRegistrationDate(taxIdentity.getGstRegistrationDate());
        dto.setGstVerified(taxIdentity.getGstVerified());
        dto.setGstRegistrationAddress(addressMapper.toDTO(taxIdentity.getGstRegistrationAddress()));
        dto.setPan(taxIdentity.getPan());
        dto.setPanVerified(taxIdentity.getPanVerified());
        dto.setVat(taxIdentity.getVat());
        dto.setTin(taxIdentity.getTin());
        return dto;
    }

    public TaxIdentity toEntity(TaxIdentityDTO taxIdentityDTO) {
        return modelMapper.map(taxIdentityDTO, TaxIdentity.class);
    }
}
