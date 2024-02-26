package com.ctytech.flierly.organization.repository;

import com.ctytech.flierly.organization.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    Boolean existsByPhone(String phone);

    Boolean existsByEmail(String email);
}
