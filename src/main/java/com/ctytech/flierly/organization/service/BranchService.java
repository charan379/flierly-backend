package com.ctytech.flierly.organization.service;

import com.ctytech.flierly.organization.dto.BranchDTO;
import com.ctytech.flierly.organization.exception.BranchServiceException;

import java.util.List;

public interface BranchService {

    BranchDTO save(BranchDTO branchDTO) throws BranchServiceException;

    BranchDTO fetch(Long id) throws BranchServiceException;

    List<BranchDTO> fetchAll();

    BranchDTO modify(Long id, BranchDTO update) throws BranchServiceException;

    BranchDTO modifyAddressById(Long branchId, Long addressId) throws BranchServiceException;

    BranchDTO modifyTaxIdentityById(Long branchId, Long taxIdentityId) throws BranchServiceException;

    Boolean isActive(Long id) throws BranchServiceException;

    Boolean existsByPhone(String phone);

    Boolean existsByEmail(String email);
}
