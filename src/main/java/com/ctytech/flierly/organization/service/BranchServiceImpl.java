package com.ctytech.flierly.organization.service;

import com.ctytech.flierly.organization.dto.BranchDTO;
import com.ctytech.flierly.organization.entity.Branch;
import com.ctytech.flierly.organization.exception.BranchServiceException;
import com.ctytech.flierly.organization.mapper.BranchMapper;
import com.ctytech.flierly.organization.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service(value = "branchService")
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private BranchMapper branchMapper;

    @Override
    public BranchDTO save(BranchDTO branchDTO) throws BranchServiceException {
        // Set isActive to true if not provided
        if (branchDTO.getIsActive() == null) branchDTO.setIsActive(true);
        // Check if branch with same phone already exists
        if (existsByPhone(branchDTO.getPhone())) throw new BranchServiceException("BranchService.PHONE_ALREADY_EXISTS");
        // Check if branch with same email already exists
        if (existsByEmail(branchDTO.getEmail())) throw new BranchServiceException("BranchService.EMAIL_ALREADY_EXISTS");

        Branch branch = branchMapper.toEntity(branchDTO);

        return branchMapper.toDTO(branchRepository.save(branch));
    }

    @Override
    public BranchDTO fetch(Long id, String... includesDTOs) throws BranchServiceException {
        Branch branch = branchRepository.findById(id).orElseThrow(() -> new BranchServiceException("BranchService.NOT_FOUND"));
        return branchMapper.toDTO(branch, includesDTOs);
    }

    @Override
    public List<BranchDTO> fetchAll(String... includesDTOs) {
        return branchRepository.findAll().stream().map(branch -> branchMapper.toDTO(branch, includesDTOs)).toList();
    }

    @Override
    public BranchDTO modify(Long id, BranchDTO update) throws BranchServiceException {
        Branch branch = branchRepository.findById(id).orElseThrow(() -> new BranchServiceException("BranchService.NOT_FOUND"));
        // Update branch name if there is any change
        if (!branch.getName().equalsIgnoreCase(update.getName())) branch.setName(update.getName());
        // Set isActive if provided
        if (update.getIsActive() != null) branch.setIsActive(update.getIsActive());
        // Update phone if there is any change
        if (!branch.getPhone().equalsIgnoreCase(update.getPhone())) {
            // Check if new phone number is already exists with another branch
            if (existsByPhone(update.getPhone()))
                throw new BranchServiceException("BranchService.PHONE_ALREADY_EXISTS");
            // If everything is ok then update new phone number
            branch.setPhone(update.getPhone());
        }
        // Update email if there is any change
        if (!branch.getEmail().equalsIgnoreCase(update.getEmail())) {
            // Check if new email is already exists with another branch
            if (existsByEmail(update.getEmail()))
                throw new BranchServiceException("BranchService.EMAIL_ALREADY_EXISTS");
            // If everything is ok then update new email address
            branch.setEmail(update.getEmail());
        }
        branch.setAlternatePhone(update.getAlternatePhone());
        return branchMapper.toDTO(branchRepository.save(branch));
    }

    @Override
    public BranchDTO modifyAddressById(Long branchId, Long addressId) throws BranchServiceException {
        Branch branch = branchRepository.findById(branchId).orElseThrow(() -> new BranchServiceException("BranchService.NOT_FOUND"));
        // Extract existing addressId from branch if exists
        Long existingAddressId = branch.getAddressId();
        // Update Address if existing addressId and new addressId are different
        if (!Objects.equals(existingAddressId, addressId)) {
            branch.setAddressId(addressId);
        }
        return branchMapper.toDTO(branchRepository.save(branch));
    }

    @Override
    public BranchDTO modifyTaxIdentityById(Long branchId, Long taxIdentityId) throws BranchServiceException {
        Branch branch = branchRepository.findById(branchId).orElseThrow(() -> new BranchServiceException("BranchService.NOT_FOUND"));
        // Extract existing taxIdentity ID from branch if exists
        Long existingTaxIdentityId = branch.getTaxIdentityId();
        // If there is not existing taxIdentity on this branch then only update provided taxIdentity
        if (existingTaxIdentityId == null) {
            branch.setTaxIdentityId(taxIdentityId);
        }
        return branchMapper.toDTO(branchRepository.save(branch));
    }

    @Override
    public Boolean isActive(Long id) throws BranchServiceException {
        return Objects.requireNonNullElse(fetch(id).getIsActive(), false);
    }

    @Override
    public Boolean existsByPhone(String phone) {
        return branchRepository.existsByPhone(phone);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return branchRepository.existsByEmail(email);
    }
}
