package com.ctytech.flierly.address.repository;

import com.ctytech.flierly.address.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    
}
