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
        return modelMapper.map(branch, BranchDTO.class);
    }

    public Branch toEntity(BranchDTO branchDTO) {
        return modelMapper.map(branchDTO, Branch.class);
    }
}
