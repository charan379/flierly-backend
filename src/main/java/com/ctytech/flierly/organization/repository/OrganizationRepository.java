package com.ctytech.flierly.organization.repository;

import com.ctytech.flierly.organization.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}
