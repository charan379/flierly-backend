package com.ctytech.flierly.organization.mapper;

import com.ctytech.flierly.address.mapper.AddressMapper;
import com.ctytech.flierly.organization.dto.BranchDTO;
import com.ctytech.flierly.organization.entity.Branch;
import com.ctytech.flierly.taxation.mapper.TaxIdentityMapper;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BranchMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Getter
    @Autowired
    private AddressMapper addressMapper;

    @Getter
    @Autowired
    private TaxIdentityMapper taxIdentityMapper;

    public BranchDTO toDTO(Branch branch) {
        if (branch == null) return null;
        BranchDTO dto = new BranchDTO();
        dto.setId(branch.getId());
        dto.setName(branch.getName());
        dto.setIsActive(branch.getIsActive());
        dto.setPhone(branch.getPhone());
        dto.setAlternatePhone(branch.getAlternatePhone());
        dto.setEmail(branch.getEmail());
        dto.setAddress(addressMapper.toDTO(branch.getAddress()));
        dto.setTaxIdentity(taxIdentityMapper.toDTO(branch.getTaxIdentity()));
        return dto;
    }

    public BranchDTO toDTO(Branch branch, boolean isMinimal) {
        if (branch == null) return null;
        if (isMinimal) {
            BranchDTO dto = new BranchDTO();
            dto.setId(branch.getId());
            dto.setName(branch.getName());
            dto.setIsActive(branch.getIsActive());
            dto.setPhone(branch.getPhone());
            dto.setAlternatePhone(branch.getAlternatePhone());
            dto.setEmail(branch.getEmail());
            return dto;
        }
        return toDTO(branch);
    }

    public Branch toEntity(BranchDTO branchDTO) {
        return modelMapper.map(branchDTO, Branch.class);
    }
}
