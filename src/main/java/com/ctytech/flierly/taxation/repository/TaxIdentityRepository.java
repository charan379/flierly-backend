package com.ctytech.flierly.taxation.repository;

import com.ctytech.flierly.taxation.entity.TaxIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaxIdentityRepository extends JpaRepository<TaxIdentity, Long> {

    Optional<TaxIdentity> findByGst(String gstNumber);

    Optional<TaxIdentity> findByPan(String pan);

    Boolean existsByGst(String gstNumber);

    Boolean existsByPan(String pan);
}
