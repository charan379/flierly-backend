package com.ctytech.flierly.address.repository;

import com.ctytech.flierly.address.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findByCode(String code);

    @Query("SELECT CASE WHEN count(C) > 0 THEN true ELSE false END FROM Country C WHERE C.code= :code")
    Boolean existsByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN count(C) > 0 THEN true ELSE false END from Country C WHERE C.dialingCode= :dialingCode")
    Boolean existsByDialingCode(@Param("dialingCode") Integer dialingCode);
}
